package com.sbux.ebs.surveyforms.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class QueryNoResultsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public QueryNoResultsException(String errorCode) {
        super(errorCode);
    }

}
