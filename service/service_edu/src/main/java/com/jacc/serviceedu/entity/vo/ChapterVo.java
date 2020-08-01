package com.jacc.serviceedu.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChapterVo {
    private String id;
    private String title;
    List<VideoVo> videoVoList;
}
