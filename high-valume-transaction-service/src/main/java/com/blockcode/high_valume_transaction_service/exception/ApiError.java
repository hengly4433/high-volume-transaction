package com.blockcode.high_valume_transaction_service.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    /** Timestamp when the error occurred */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    /** HTTP status code */
    private int status;

    /** HTTP status text, e.g. "Not Found" */
    private String error;

    /** The exception message */
    private String message;

    /** The request path (optional) */
    private String path;

    public ApiError(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status    = status.value();
        this.error     = status.getReasonPhrase();
        this.message   = message;
        this.path      = path;
    }
}
