package com.zysj.etl_excute.controller;

import com.zysj.etl_excute.service.impl.EtlExcuteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试etl任务调用
 *
 * @author : Created by Unicorn
 * @date : Created in 2019/9/19
 */
@RestController
public class EtlExcuteController {
    @Autowired
    private EtlExcuteServiceImpl etlExcuteService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/etl")
    public void etlExcute() {
        etlExcuteService.executeEtlJob("/etl/1/2");
    }
}
