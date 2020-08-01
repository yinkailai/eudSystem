package com.jacc.serviceedu.entity.vo;

import com.jacc.serviceedu.entity.EduSubject;
import lombok.Data;

import java.util.List;
@Data
public class SubjectVoOne {
    private String id;
    private String title;
    private List<SubjectVoTwo> children;
}
