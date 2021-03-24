package com.heroe.demo.model;

import java.util.Date;

/**
 *
 * @author w45776304
 */
public class ErrorDetails {
    
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
         super();
         this.timestamp = timestamp;
         this.message = message;
         this.details = details;
    }

    public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}
