package com.jacc.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jacc.servicebase.exceptionhandler.GlobalExceptionHandler;
import com.jacc.serviceedu.entity.EduSubject;
import com.jacc.serviceedu.entity.vo.ExcelSubjectData;
import com.jacc.serviceedu.service.EduSubjectService;

public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public ExcelSubjectListener() {
    }
    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData==null) {
            return;
        }
        //判断是否存在一级分类
        EduSubject existOne = this.isExistOne(eduSubjectService, excelSubjectData.getOneName());
        //如果不存在，则加入
        if (existOne==null){
            existOne = new EduSubject();
            existOne.setTitle(excelSubjectData.getOneName());
            existOne.setParentId("0");
            eduSubjectService.save(existOne);
        }
        String pid = existOne.getId();
        //判断是否存在二级分类
        EduSubject existTwo = this.isExistTwo(eduSubjectService, excelSubjectData.getTwoName(), pid);
        //如果不存在，则加入
        if(existTwo==null){
            existTwo = new EduSubject();
            existTwo.setParentId(pid);
            existTwo.setTitle(excelSubjectData.getTwoName());
            eduSubjectService.save(existTwo);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 判断一级分类是否存在
     * @return
     */
    public EduSubject isExistOne(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject subject = eduSubjectService.getOne(wrapper);
        return subject;
    }
    /**
     * 判断二级分类是否存在
     * @return
     */
    public EduSubject isExistTwo(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject subject = eduSubjectService.getOne(wrapper);
        return subject;
    }
}
