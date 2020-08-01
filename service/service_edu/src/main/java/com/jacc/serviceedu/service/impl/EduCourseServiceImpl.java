package com.jacc.serviceedu.service.impl;

import com.jacc.serviceedu.entity.EduCourse;
import com.jacc.serviceedu.entity.EduCourseDescription;
import com.jacc.serviceedu.entity.vo.CourseVo;
import com.jacc.serviceedu.mapper.EduCourseMapper;
import com.jacc.serviceedu.service.EduCourseDescriptionService;
import com.jacc.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    @Override
    public String saveCourse(CourseVo courseVo) {
        //课程保存
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseVo,eduCourse);
        baseMapper.insert(eduCourse);
        //课程描述保存
        String description = courseVo.getDescription();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(description);
        courseDescription.setId(eduCourse.getId());
        if (description!=null&&description!=""){
            courseDescriptionService.save(courseDescription);
        }
        return eduCourse.getId();
    }
}
