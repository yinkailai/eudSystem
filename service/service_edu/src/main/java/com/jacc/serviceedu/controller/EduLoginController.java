package com.jacc.serviceedu.controller;

import com.jacc.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceEdu/user")
@CrossOrigin
public class EduLoginController {

    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){

        return R.ok().data("roles","admin").data("name","admin").data("avatar","https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg");

    }

}
