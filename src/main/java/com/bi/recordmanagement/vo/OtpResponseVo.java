package com.bi.recordmanagement.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class OtpResponseVo {
    /**
     * http://localhost:8989/api/otps?key=aryabhatta&pageNumber=0&pageSize=1&phone=8750090669
     * [
     * {
     * "otp": "67836",
     * "creationDate": "2019-08-12T12:13:44.000+0000",
     * "expiredON": "2019-08-12T12:43:44.000+0000",
     * "startTime": "2019-08-12T12:13:44.000+0000",
     * "otpFor": "8750090669"
     * }
     * ]
     */

    private String otp;
    private Date creationDate;
    private Date expiredON;
    private Date startTime;
    private String otpFor;
}
