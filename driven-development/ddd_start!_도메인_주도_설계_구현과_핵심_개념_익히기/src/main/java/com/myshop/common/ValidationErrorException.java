package com.myshop.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorException extends RuntimeException {
    private final List<ValidationError> errors;
}
