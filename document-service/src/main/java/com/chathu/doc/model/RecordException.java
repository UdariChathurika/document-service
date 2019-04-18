package com.chathu.doc.model;

public class RecordException extends RuntimeException {

    public RecordException(String non_existing_record) {
        super();
    }

    public RecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
