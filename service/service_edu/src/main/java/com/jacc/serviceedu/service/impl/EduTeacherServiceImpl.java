package com.jacc.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jacc.serviceedu.entity.EduTeacher;
import com.jacc.serviceedu.mapper.EduTeacherMapper;
import com.jacc.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yinkl
 * @since 2020-06-21
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public void deleteById(String id) {
        eduTeacherMapper.deleteById(id);
    }

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, EduTeacher teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String career = teacherQuery.getCareer();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (level!=null&&!"".equals(level)) {
            queryWrapper.eq("level", level);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
