package com.gulbi.Backend.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionMetaDataDto {
    private final String args;
    private final String className;
    private final String stackTrace;

    public ExceptionMetaDataDto(Object args, String className, Throwable stackTrace) {
        this.args = convertArgsToJson(args);
        this.className = className;
        this.stackTrace = stackTrace == null ? "" : extractStackTrace(stackTrace);
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getClassName() {
        return className;
    }

    public String getArgs() {
        return args;
    }

    private String extractStackTrace(Throwable throwable) {
        StringBuilder stackTraceBuilder = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            stackTraceBuilder.append(element).append("\n");
        }
        return stackTraceBuilder.toString();
    }

    private String convertArgsToJson(Object args) {
        try {
            return new ObjectMapper().writeValueAsString(args);
        } catch (Exception e) {
            return "Error converting args to JSON";
        }
    }
}