package com.gcl.myt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = -5484747170880095996L;

	public BadRequestException(String msg, Throwable t) {
		super(msg, t);
	}

	public BadRequestException(String msg) {
		super(msg);
	}

}
