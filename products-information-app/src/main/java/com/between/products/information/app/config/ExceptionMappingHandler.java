package com.between.products.information.app.config;

import com.between.products.information.common.exception.ErrorInfo;
import com.between.products.information.common.exception.ProductInformationException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionMappingHandler {

    @ExceptionHandler(ProductInformationException.class)
    public ResponseEntity<ErrorInfo> handleError(ProductInformationException ex) {
        ErrorInfo errorInfo =
                new ErrorInfo(
                        Integer.valueOf(ex.getHttpStatus().value()),
                        ex.getHttpStatus(),
                        DateTime.now(),
                        ex.getMessage());
        return new ResponseEntity<>(errorInfo, errorInfo.getStatus());
    }
}
