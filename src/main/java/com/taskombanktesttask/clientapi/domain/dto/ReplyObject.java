package com.taskombanktesttask.clientapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Reply object for api
 */
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyObject {

    /**
     * Indicates is request successfully
     * processed with no errors
     */
    private boolean success;
    /**
     * Data of response. Be null if request is not successful
     */
    private Object responseData;
    /**
     * If request finished with error
     * this field will contain error description.
     * Empty if response successful
     */
    private String errorMessage;

    /**
     * Creates empty success response
     *
     * @return new empty success response
     */
    public static ReplyObject success(){

        ReplyObject reply = new ReplyObject();
        reply.success = true;
        return reply;

    }

    /**
     * Create successful reply object
     * and fills it body with given value
     *
     * @param response new reply body
     * @return new successful reply object
     */
    public static ReplyObject success(Object response){

        ReplyObject reply = new ReplyObject();
        reply.success = true;
        reply.responseData = response;
        return reply;

    }

    public static ReplyObject fail(String message){

        ReplyObject reply = new ReplyObject();
        reply.success = false;
        reply.errorMessage = message;
        return reply;

    }

    private ReplyObject(){

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
