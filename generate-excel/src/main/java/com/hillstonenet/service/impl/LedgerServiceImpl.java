package com.hillstonenet.service.impl;

import com.hillstonenet.config.DataConfig;
import com.hillstonenet.constant.LedgerConstant;
import com.hillstonenet.po.NeInfo;
import com.hillstonenet.service.LedgerService;
import com.hillstonenet.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
                new NeInfo("device2", 2, "654321", (byte) 1, "R10")
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
        try (OutputStream output = new FileOutputStream("./testExcel.xls")) {
            hwk.write(output);
            output.flush();
        } catch (IOException e) {
            log.error("io error");
        }
    }
}