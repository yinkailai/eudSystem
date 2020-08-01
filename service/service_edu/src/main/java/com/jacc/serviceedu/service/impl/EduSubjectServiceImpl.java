package com.jacc.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jacc.serviceedu.entity.EduSubject;
import com.jacc.serviceedu.entity.vo.ExcelSubjectData;
import com.jacc.serviceedu.entity.vo.SubjectVoOne;
import com.jacc.serviceedu.entity.vo.SubjectVoTwo;
import com.jacc.serviceedu.listener.ExcelSubjectListener;
import com.jacc.serviceedu.mapper.EduSubjectMapper;
import com.jacc.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void readExcel(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<SubjectVoOne> getTreeList() {
        //最终要的到的数据列表
        ArrayList<SubjectVoOne> SubjectVoOneArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //创建一级类别vo对象
            SubjectVoOne subjectVoOne = new SubjectVoOne();
            BeanUtils.copyProperties(subject, subjectVoOne);
            SubjectVoOneArrayList.add(subjectVoOne);
            //填充二级分类vo数据
            ArrayList<SubjectVoTwo> SubjectVoTwoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if (subject.getId().equals(subSubject.getParentId())) {
                    //创建二级类别vo对象
                    SubjectVoTwo subjectVo = new SubjectVoTwo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    SubjectVoTwoArrayList.add(subjectVo);
                }
            }
            subjectVoOne.setChildren(SubjectVoTwoArrayList);
        }
        return SubjectVoOneArrayList;
    }
}
