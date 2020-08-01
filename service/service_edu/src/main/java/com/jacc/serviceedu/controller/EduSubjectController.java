package com.jacc.serviceedu.controller;


import com.jacc.commonutils.R;
import com.jacc.serviceedu.entity.vo.SubjectVoOne;
import com.jacc.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author yinkl
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/serviceEdu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("readExcel")
    public R readExcel(MultipartFile file) {
        eduSubjectService.readExcel(file,eduSubjectService);
        return R.ok();
    }
    @GetMapping("treeDateList")
    public R treeDateList() {
        List<SubjectVoOne> treeList =  eduSubjectService.getTreeList();
        return R.ok().data("treeList",treeList);
    }

}

