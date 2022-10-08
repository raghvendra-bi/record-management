package com.bi.recordmanagement.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_access_token")
@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AccessToken implements Serializable {

    @Id
    private String guid;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "expiration_time")
    private String expiryTime;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "client_id")
    private String clientId;

}

