package com.hillstonenet.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-11-21 17:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeInfo {

    private String neName;

    private Integer neId;

    private String sn;

    private Byte online;

    private String version;
}