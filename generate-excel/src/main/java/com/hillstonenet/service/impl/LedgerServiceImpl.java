package com.hillstonenet.service.impl;

import com.hillstonenet.config.DataConfig;
import com.hillstonenet.constant.LedgerConstant;
import com.hillstonenet.po.NeInfo;
import com.hillstonenet.service.LedgerService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:24
 */
@Service
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
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        for (NeInfo neInfo : neInfoList) {
            Map<String, Object> dataMap = new HashMap<>();
            List<String> fieldNameList = LedgerConstant.AdcDeviceLedgerHeader.stream().map(key -> key.substring(key.lastIndexOf("."))).collect(Collectors.toList());
            for (String fieldName : fieldNameList) {
                dataMap.put(fieldName, )
            }
        }


        HSSFWorkbook hwk = new HSSFWorkbook();
        HSSFSheet sheet = hwk.createSheet("台账");
        HSSFRow headerRow = sheet.createRow(0);

    }
}