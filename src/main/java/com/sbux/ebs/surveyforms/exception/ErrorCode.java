package com.sbux.ebs.surveyforms.exception;

public enum ErrorCode {
	NoRecordsFound("9001002"), InValidOption("9001004");

	public final String value;

	ErrorCode(String value) {
		this.value = value;
	}
}
