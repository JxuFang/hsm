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
            "adc.device.ledger.header.neName",
            "adc.device.ledger.header.neId",
            "adc.device.ledger.header.sn",
            "adc.device.ledger.header.online",
            "adc.device.ledger.header.version"
    );

    public enum OnlineStatus {
        ONLINE(0, "ledger.device.online"), OFFLINE(1, "ledger.device.offline");

        private final int type;

        private final String key;

        OnlineStatus(int type, String key) {
            this.type = type;
            this.key = key;
        }
        public String getKey() {
            return key;
        }

        public static OnlineStatus getKeyByType(int type) {
            for (OnlineStatus status : values()) {
                if (status.type == type) {
                    return status;
                }
            }
            return null;
        }
    }

}