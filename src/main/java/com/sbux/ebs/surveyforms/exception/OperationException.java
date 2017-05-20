package com.sbux.ebs.surveyforms.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class OperationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OperationException(String errorCode) {
        super(errorCode);
    }
}