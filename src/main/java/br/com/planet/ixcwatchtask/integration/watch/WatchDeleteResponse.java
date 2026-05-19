package br.com.planet.ixcwatchtask.integration.watch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchDeleteResponse {

    @JsonProperty("HasError")
    private Boolean hasError;

    @JsonProperty("ErrorMessage")
    private String errorMessage;

    @JsonProperty("ErrorNumber")
    private Integer errorNumber;

    @JsonProperty("IsValidationError")
    private Boolean isValidationError;

    @JsonProperty("Result")
    private Object result;

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    public Boolean getValidationError() {
        return isValidationError;
    }

    public void setValidationError(Boolean validationError) {
        isValidationError = validationError;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "WatchDeleteResponse{" +
                "hasError=" + hasError +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorNumber=" + errorNumber +
                ", isValidationError=" + isValidationError +
                ", result=" + result +
                '}';
    }
}
