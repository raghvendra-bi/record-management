package com.bi.recordmanagement.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class ExceptionResponse {

	private int code;
	private boolean response;
	private List<String> errorMessages = new ArrayList<>();
	private Date time = new Date();
	private String path;
	
	public void addErrorMessage(String errorMsg) {
		errorMessages.add(errorMsg);
	}
}
