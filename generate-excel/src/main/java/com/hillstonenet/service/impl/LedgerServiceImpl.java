package com.hillstonenet.service.impl;

import com.hillstonenet.config.DataConfig;
import com.hillstonenet.constant.LedgerConstant;
import com.hillstonenet.po.NeInfo;
import com.hillstonenet.service.LedgerService;
import com.hillstonenet.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:24
 */
@Service
@Slf4j
public class LedgerServiceImpl implements LedgerService {

//    @Autowired
//    private ApplicationContext applicationContext;

    @Resource
    private DataConfig data;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest httpServletRequest;

//    @Autowired
//    private A testA;

//    @Autowired
//    private ResourceBundleMessageSource getMessageSource;

    private static List<NeInfo> neInfoList;

    static {
        neInfoList = Lists.newArrayList(
                new NeInfo("device1", 1, "123456", (byte) 0, "R9"),
                new NeInfo("device2", 2, "654321", (byte) 1, "R10"),
                new NeInfo("device2", 1, "123456", (byte) 0, "R9"),
                new NeInfo("device3", 2, "654321", (byte) 1, "R10"),
                new NeInfo("device3", 1, "123456", (byte) 0, "R9"),
                new NeInfo("device3", 2, "654321", (byte) 1, "R10"),
                new NeInfo("device4", 1, "123456", (byte) 0, "R9"),
                new NeInfo("device4", 2, "654321", (byte) 1, "R10"),
                new NeInfo("device5", 2, "654321", (byte) 1, "R10")
        );
    }

    @Override
    public void generate() {
        List<Map<String, Object>> dataMapList = ExcelUtil.getPaddingData(neInfoList, NeInfo.class);

        List<String> fieldNameList = LedgerConstant.AdcDeviceLedgerHeader.stream().map(key -> key.substring(key.lastIndexOf(".")+1)).collect(Collectors.toList());
        List<String> adcDeviceLedgerHeader = LedgerConstant.AdcDeviceLedgerHeader.stream()
                .map(header -> messageSource.getMessage(header, new Object[]{}, httpServletRequest.getLocale()))
                .collect(Collectors.toList());
        HSSFWorkbook hwk = ExcelUtil.generateExcel(adcDeviceLedgerHeader, dataMapList, fieldNameList);
        HSSFSheet sheetAt = hwk.getSheetAt(0);
        HSSFCellStyle style = hwk.createCellStyle();
//        ExcelUtil.mergeAndCenter(sheetAt, 1, 2, 0, style);
//        ExcelUtil.mergeAndCenter(sheetAt, 3, 4, 0, style);

        int prevRow = 1;
        String prevValue = String.valueOf(sheetAt.getRow(1).getCell(0));

        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
            HSSFRow curRow = sheetAt.getRow(i);
            if (curRow != null) {
                HSSFCell firstCell = curRow.getCell(0);
                if (prevValue.equals(firstCell.toString())) {
                    continue;
                } else {
                    ExcelUtil.mergeAndCenter(sheetAt, prevRow, curRow.getRowNum() - 1, 0, style);
                    prevRow = curRow.getRowNum();
                    prevValue = firstCell.toString();
                }
            }
        }
        if (sheetAt.getLastRowNum() - prevRow > 0) {
            ExcelUtil.mergeAndCenter(sheetAt, prevRow, sheetAt.getLastRowNum(), 0, style);
        }

        try (OutputStream output = Files.newOutputStream(Paths.get("./testExcel.xls"))) {
            hwk.write(output);
            output.flush();
        } catch (IOException e) {
            log.error("io error");
        }
    }
}