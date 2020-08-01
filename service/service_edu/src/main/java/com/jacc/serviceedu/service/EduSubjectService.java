package com.jacc.serviceedu.service;

import com.jacc.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jacc.serviceedu.entity.vo.SubjectVoOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    void readExcel(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectVoOne> getTreeList();
}
