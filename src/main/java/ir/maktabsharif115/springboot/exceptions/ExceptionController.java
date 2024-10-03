package ir.maktabsharif115.springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
//@RestControllerAdvice
public class ExceptionController /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView getRuntimeException(RuntimeException ex) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", ex.getMessage());
        return view;
    }

    @ExceptionHandler(value = {GeneralRuntimeException.class})
    public ResponseEntity<ErrorDTO> getGeneralRuntimeException(GeneralRuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorDTO(
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        ex.getMessage(),
                        ex.getHttpStatus().name()
                ),
                ex.getHttpStatus()
        );
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDTO implements Serializable {
        private String date;
        private String message;
        private String status;
    }

}
