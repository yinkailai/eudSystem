package com.jacc.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jacc.serviceedu.entity.EduTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yinkl
 * @since 2020-06-21
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, EduTeacher teacherQuery);
    void deleteById(String id);
}
