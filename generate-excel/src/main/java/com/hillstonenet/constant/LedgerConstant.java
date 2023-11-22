package com.hillstonenet.constant;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-22 17:33
 */
public class LedgerConstant {

    private LedgerConstant(){}

    public static final List<String> AdcDeviceLedgerHeader = Lists.newArrayList(
            "adc.device.ledger.neName",
            "adc.device.ledger.neId",
            "adc.device.ledger.sn",
            "adc.device.ledger.online",
            "adc.device.ledger.version"
    );
}