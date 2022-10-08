package com.bi.recordmanagement.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_activity")
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userName;
    @Enumerated(EnumType.STRING)
    private LoginStatus status;
    private Integer attempts;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String region;
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    private String ipAddress;
    @Enumerated(EnumType.STRING)
    private ActivityType type;
}

