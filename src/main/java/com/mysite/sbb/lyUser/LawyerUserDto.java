package com.mysite.sbb.lyUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawyerUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
