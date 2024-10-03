package ir.maktabsharif115.springboot.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public class GeneralRuntimeException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

}
