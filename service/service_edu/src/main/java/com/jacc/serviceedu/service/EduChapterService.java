package com.jacc.serviceedu.service;

import com.jacc.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jacc.serviceedu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-26
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> chapterList(String id);
}
