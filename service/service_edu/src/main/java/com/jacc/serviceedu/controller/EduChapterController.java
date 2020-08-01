package com.jacc.serviceedu.controller;


import com.jacc.commonutils.R;
import com.jacc.serviceedu.entity.vo.ChapterVo;
import com.jacc.serviceedu.service.EduChapterService;
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
@RequestMapping("/serviceEdu/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("/chapterList/{id}")
    public R chapterList(@PathVariable String id){
        List<ChapterVo> chapterVoList = eduChapterService.chapterList(id);
        return R.ok().data("chapterVoList",chapterVoList);
    }

}

