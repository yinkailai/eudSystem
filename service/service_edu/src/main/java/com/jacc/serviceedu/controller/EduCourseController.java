package com.jacc.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jacc.commonutils.R;
import com.jacc.serviceedu.entity.EduCourse;
import com.jacc.serviceedu.entity.EduSubject;
import com.jacc.serviceedu.entity.EduTeacher;
import com.jacc.serviceedu.entity.vo.CourseVo;
import com.jacc.serviceedu.service.EduCourseService;
import com.jacc.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yinkl
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/serviceEdu/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 保存课程
     * @param courseVo
     * @return
     */
    @PostMapping("/saveCourse")
    public R save(@RequestBody(required = false) CourseVo courseVo){
        try {
            String id = eduCourseService.saveCourse(courseVo);
            return R.ok().data("courseId",id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取课程一级分类
     * @return
     */
    @GetMapping("/getSubjectParent")
    public R getSubjectParent(){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>();
        wrapper.eq("parent_id",0);
        wrapper.eq("is_deleted",0);
        List<EduSubject> list = eduSubjectService.list(wrapper);
        return R.ok().data("list",list);
    }
    /**
     * 获取课程二级分类
     * @return
     */
    @GetMapping("/getSubjectList/{id}")
    public R getSubjectList(@PathVariable  String id){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        wrapper.eq("is_deleted",0);
        List<EduSubject> list = eduSubjectService.list(wrapper);
        return R.ok().data("list",list);
    }

    /**
     * 根据id获取数据
    * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id){
        EduCourse courseInfo = eduCourseService.getById(id);
        return R.ok().data("courseInfo",courseInfo);
    }
}

