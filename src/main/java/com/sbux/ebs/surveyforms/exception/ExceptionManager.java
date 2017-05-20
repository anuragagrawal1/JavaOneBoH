package com.sbux.ebs.surveyforms.exception;

import java.util.Hashtable;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sbux.ebs.surveyforms.dto.Error;

public class ExceptionManager {

	final static Hashtable<ErrorCode, String> errorMapping = new Hashtable<ErrorCode, String>();
	static {
		errorMapping.put(ErrorCode.NoRecordsFound, "No records found for the search criteria provided.");
		errorMapping.put(ErrorCode.InValidOption, "Invalid option passed in call.");
	}

	public Error getErrorMessage(Throwable exception) {
		String errorCode = exception.getMessage();
		Error error = new Error();
		error.setCode(errorCode);
		error.setMessage("Unknown Error");

		Hashtable<String, String> errorMapping = new Hashtable<String, String>();

		errorMapping.put("9001002", "No records found for the search criteria provided.");
		errorMapping.put("9001004", "Invalid option passed in call.");
		errorMapping.put("9001005", "Invalid date/time format passed.");

		if (errorMapping.get(errorCode) == null) {
			error.setMessage("Unknown Error");
		} else {
			error.setMessage(errorMapping.get(errorCode));
		}

		return error;
	}

	private static Error getErrorMessage(ErrorCode errorCode) {
		return new Error(errorCode.value,
				errorMapping.containsKey(errorCode) ? errorMapping.get(errorCode) : "Unknown Error");
	}

	public static Response buildErrorResponse(Throwable exception, Status errorStatus) {
		exception.printStackTrace();
		return Response.status(errorStatus).entity(exception.toString()).build();
	}

	public static Response buildCustomErrorResponse(ErrorCode errorCode, Status errorStatus) {
		return Response.status(errorStatus).entity(getErrorMessage(errorCode)).build();
	}
}
