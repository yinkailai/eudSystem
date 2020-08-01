package com.jacc.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jacc.commonutils.R;
import com.jacc.serviceedu.entity.EduTeacher;
import com.jacc.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author yinkl
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/serviceEdu/edu-teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有数据，返回统一结果格式 R
     * @return
     */
    @GetMapping("/findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("list",list);
    }

    /**
     * 分页查询所有
     * @return
     */
    @PostMapping("/pageFindAll/{current}/{limit}")
    public R pageFindAll(@PathVariable Long current,
                         @PathVariable Long limit,
                         @RequestBody(required = false) EduTeacher teacherQuery) {
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.pageQuery(page,teacherQuery);
        //总记录数
        long total = page.getTotal();
        //数据集合
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("records",records).data("total",total);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@PathVariable String id){
        try {
            eduTeacherService.deleteById(id);
            return R.ok();
        }catch (Exception e) {
            return R.error();
        }

    }

    /**
     * 保存
     * @param teacher
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody EduTeacher teacher){
        try {
            eduTeacherService.saveOrUpdate(teacher);
            return R.ok();
        }catch (Exception e){
            return R.error();
        }
    }

    /**
     * 根据id查询
    * @param id
     * @return
     */
    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        try {
            EduTeacher teacher = eduTeacherService.getById(id);
            return R.ok().data("teacher",teacher);
        }catch (Exception e){
            return R.error();
        }

    }

}

