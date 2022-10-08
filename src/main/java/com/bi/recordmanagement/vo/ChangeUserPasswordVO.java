package com.bi.recordmanagement.vo;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChangeUserPasswordVO {
    @NotBlank(message = "{err.user.oldPassword.empty}")
    private String oldPassword;

    @NotBlank(message = "{err.user.newPassword.empty}")
    private String newPassword;

    private String loginName;
}
