package com.app.exceptions;

import org.springframework.http.HttpStatus;

public record ApiException(HttpStatus status, String message){}
