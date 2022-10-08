package com.bi.recordmanagement.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter@Setter
@ToString
public class UserActivityVo  implements Serializable {
    private String ip;
    private String type;
    private String continent_code;
    private String continent_name;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip;
    private float latitude;
    private float longitude;
}
