package com.bi.recordmanagement.vo;

import java.util.Date;

import com.bi.recordmanagement.models.ActivityType;
import com.bi.recordmanagement.models.LoginStatus;
import com.bi.recordmanagement.views.PublisherView.PublisherBasicView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LoginEventVo {
    @JsonView(PublisherBasicView.class)
    private String ipAddress;
    @JsonView(PublisherBasicView.class)
    private Date dateTime;
    @JsonView(PublisherBasicView.class)
    private String userName;
    @JsonView(PublisherBasicView.class)
    private Long userId;
    @JsonView(PublisherBasicView.class)
    private Long attempts;
    @JsonView(PublisherBasicView.class)
    private LoginStatus loginStatus;
    @JsonView(PublisherBasicView.class)
    private ActivityType activityType;

    public LoginEventVo(){}

}
