package com.bi.recordmanagement.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;


public class RMServiceValidationEcxception extends ConstraintViolationException {
    public RMServiceValidationEcxception(String message, SQLException root, String constraintName) {
        super(message, root, constraintName);
        // TODO Auto-generated constructor stub
    }
}
