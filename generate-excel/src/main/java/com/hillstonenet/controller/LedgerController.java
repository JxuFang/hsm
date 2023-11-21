package com.hillstonenet.controller;

import com.hillstonenet.service.LedgerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:19
 */
@RestController
@RequestMapping("/ledger")
public class LedgerController {

    @Resource
    private LedgerService ledgerService;


    @GetMapping("/generate")
    public void generateLedger() {
        ledgerService.generate();
    }
}