package com.gulbi.Backend.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionMetaData {
    private static final String NO_ARGS = "NO_ARGS_IN_CLASS";
    private static final String NO_STACKTRACE = "NO_STACKTRACE_IN_THIS_EXCEPTION";
    private static final String CANT_PARSE_ARGS_TO_JSON = "Error converting args to JSON";
    private String args;
    private String className;
    private String stackTrace;

    public ExceptionMetaData(Object args, String className, Throwable stackTrace) {
        this.args = convertArgsToJson(args);
        this.className = className;
        this.stackTrace = extractStackTrace(stackTrace);
    }

    public ExceptionMetaData(String className, Throwable stackTrace){
        this.args = NO_ARGS;
        this.className = className;
        this.stackTrace = extractStackTrace(stackTrace);

    }

    public ExceptionMetaData(Object args, String className){
        this.args = convertArgsToJson(args);
        this.className = className;
        this.stackTrace = NO_STACKTRACE;

    }
    public ExceptionMetaData(String className){
        this.args = NO_ARGS;
        this.className = className;
        this.stackTrace = NO_STACKTRACE;
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
            return CANT_PARSE_ARGS_TO_JSON;
        }
    }
}