package com.hillstonenet.service.impl;

import com.hillstonenet.config.DataConfig;
import com.hillstonenet.service.LedgerService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:24
 */
@Service
public class LedgerServiceImpl implements LedgerService {

    @Resource
    private DataConfig data;

    @Override
    public void generate() {
        HSSFWorkbook hwk = new HSSFWorkbook();
        HSSFSheet sheet = hwk.createSheet("台账");
        HSSFRow headerRow = sheet.createRow(0);
    }
}