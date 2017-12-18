package cz.muni.fi.pa165.carparkmanager.rest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jakub Juřena <445319>
 */
public class ControllerResponse {
    
    public static <T> ResponseEntity processResponse(T response) throws Exception {
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
}
