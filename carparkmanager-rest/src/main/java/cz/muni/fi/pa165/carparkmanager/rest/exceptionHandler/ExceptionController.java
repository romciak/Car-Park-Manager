package cz.muni.fi.pa165.carparkmanager.rest.exceptionHandler;

import java.security.InvalidParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jakub Juřena <445319>
 */
@ControllerAdvice
public class ExceptionController {
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidParameterException.class)
    public void handleException() {
        
    }
    
}
