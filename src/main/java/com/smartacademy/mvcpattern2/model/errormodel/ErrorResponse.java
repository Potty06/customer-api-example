package com.smartacademy.mvcpattern2.model.errormodel;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errorDescription"
})

public class ErrorResponse {

    @JsonProperty("errorDescription")
    private String errorDescription;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDesciption) {
        this.errorDescription = errorDesciption;
    }
}
