package com.egen.order.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse implements Serializable {

    private static final String FAILURE_STATUS = "FAILURE";
    private final String status;
    private List<String> errorMessages;

    public ErrorResponse(List<String> errorMessages) {
        this.status = FAILURE_STATUS;
        this.errorMessages = errorMessages;
    }

    public ErrorResponse(String errorMessage) {
        this.status = FAILURE_STATUS;
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(errorMessage);
        this.errorMessages = errorMessages;
    }
}
