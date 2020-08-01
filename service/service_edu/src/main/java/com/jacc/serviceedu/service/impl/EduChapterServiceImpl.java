package com.jacc.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jacc.serviceedu.entity.EduChapter;
import com.jacc.serviceedu.entity.EduVideo;
import com.jacc.serviceedu.entity.vo.ChapterVo;
import com.jacc.serviceedu.entity.vo.VideoVo;
import com.jacc.serviceedu.mapper.EduChapterMapper;
import com.jacc.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jacc.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yinkl
 * @since 2020-07-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> chapterList(String id) {
        //根据课程id获取所有章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper();
        wrapper.eq("course_id",id);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        //最终数据
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            chapterVoList.add(chapterVo);
            //根据课程id获取所有小节
            QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_id",id);
            List<EduVideo> list = eduVideoService.list(queryWrapper);
            //存储小节list
            List<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo : list) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setVideoVoList(videoVoList);
        }

        return chapterVoList;
    }
}
