package com.example.appinventiv.base;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

	
	private static final long serialVersionUID = -7413795735883986236L;
	
    private boolean        status=false;
    private String            message;
    private String            statusCode;

    public ServiceResponse() {

    }

	public ServiceResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServiceResponse(boolean status, String errMsg, int statusCode) {
		this.status = status;
		this.message = errMsg;
		this.statusCode = statusCode+"";
	}


	/**
     * @return the successful
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the successful to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponse(ServiceResponse response) {
        this.status = response.isStatus();
        this.message = response.getMessage();
        this.statusCode = response.getStatusCode();
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
