package com.jacc.serviceedu.service;

import com.jacc.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jacc.serviceedu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-26
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(CourseVo courseVo);
}
