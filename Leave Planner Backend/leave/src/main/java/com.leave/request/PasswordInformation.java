package com.leave.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordInformation {
    String rollNumber;
    String token;
    String oldPassword;
    String newPassword;
}
