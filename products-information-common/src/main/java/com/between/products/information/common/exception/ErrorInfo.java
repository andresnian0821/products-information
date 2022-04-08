package com.between.products.information.common.exception;

import com.between.products.information.common.json.deserializer.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorInfo {

    private HttpStatus status;
    private Integer code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime timestamp;

    private String message;
    private List<String> errors;

    public ErrorInfo(
            Integer code, HttpStatus status, DateTime timestamp, String message, List<String> errors) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public ErrorInfo(Integer code, HttpStatus status, DateTime timestamp, String message) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ErrorInfo(HttpStatus status, DateTime timestamp, String message, List<String> errors) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }
}