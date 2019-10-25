package com.zysj.etl_manage.controller;

import com.zysj.etl_manage.service.EtlNeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/6 14:20
 */
@RestController
@RequestMapping("/ceshi")
public class EtlGenerationController {

    @Autowired
    EtlNeedsService etlNeedsService;

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    /**
     * 企业联合奖惩详细记录；
     */
    @RequestMapping("/test")
    public int getRewardAndPunishInfo() {
        int i = etlNeedsService.etlGenerateService();
        return i;
    }


}
