package com.menu.demo.Dtos;

import lombok.Data;

@Data
public class ChangePasswordReq {
	private String oldPassword;
	private String newPassword;

}
