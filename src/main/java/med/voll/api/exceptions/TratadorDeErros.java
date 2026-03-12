package med.voll.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());

        body.put("status", HttpStatus.NOT_FOUND.value());

        body.put("error", "Not Found");

        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());

        body.put("status", HttpStatus.BAD_REQUEST.value());

        body.put("error", "Validation Error");

        List<String> messages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .toList();

        body.put("messages", messages);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroLogin() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Usuário ou senha inválidos");
    }
}


