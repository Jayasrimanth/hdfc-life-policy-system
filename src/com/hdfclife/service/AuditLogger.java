package com.hdfclife.service;

import com.hdfclife.exception.PolicyNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditLogger implements AutoCloseable {
    private final PrintWriter writer;

    public AuditLogger(String filename) {
        try {
            this.writer = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            throw new PolicyNotFoundException("Failed to initialize AuditLogger",e);
        }
    }

    public void log(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    @Override
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}