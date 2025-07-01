package api.erp.solarerp.infrastructure.exceptionHandler;

import api.erp.solarerp.infrastructure.exceptions.DuplicatedEntryException;
import api.erp.solarerp.infrastructure.exceptions.ExceptionResponse;
import api.erp.solarerp.infrastructure.exceptions.IllegalPhoneFormatException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(Exception e, HttpServletRequest request) {

        String message;
        Set<String> details;

        switch (e) {
            case MethodArgumentNotValidException ex -> {
                message = "Violação da restrição no JSON enviado";
                details = ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toSet());
            }
            case ConstraintViolationException ex -> {
                message = "Violação de restrição nos parâmetros da URL";
                details = ex.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toSet());
            }
            default -> {
                // Fallback inesperado, embora improvável aqui
                message = "Erro de validação não especificado";
                details = Set.of(e.getMessage());
            }
        }
        if (details.size() == 1)
            return buildResponse(request, HttpStatus.BAD_REQUEST, message, details.iterator().next());
        return buildResponse(request, HttpStatus.BAD_REQUEST, message, details);
    }

    @ExceptionHandler(DuplicatedEntryException.class)
    ResponseEntity<ExceptionResponse> handleDuplicatedEntryException(
            DuplicatedEntryException e,
            HttpServletRequest request
    ) {
        return buildResponse(
            request,
            HttpStatus.CONFLICT,
            "Entrada duplicada",
            e.getMessage()
        );
    }

    @ExceptionHandler(IllegalPhoneFormatException.class)
    ResponseEntity<ExceptionResponse> handleIllegalPhoneFormatException(
            IllegalPhoneFormatException e,
            HttpServletRequest request
    ) {
        return buildResponse(
            request,
            HttpStatus.BAD_REQUEST,
            "Formato de telefone inválido",
            e.getMessage()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        log.warn("Falha na desserialização do JSON: {}", e.getMessage());

        String message = "O corpo da requisição contém um formato inválido.";
        String details = "Um ou mais campos foram enviados com tipos de dados incorretos."; // Mensagem padrão

        return buildResponse(
                request,
                HttpStatus.BAD_REQUEST,
                message,
                details
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResponse> handleException(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("Erro inesperado no servidor: {}", e.getMessage(), e);

        return buildResponse(
                request, HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno no servidor!",
                "Por favor, contate o suporte."
        );
    }

    // -- helper --

    private static <T> ResponseEntity<ExceptionResponse> buildResponse(
        HttpServletRequest request, HttpStatus status, String message, Object details
    ) {
        var responseBody = new ExceptionResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                details,
                request.getRequestURI()
        );
        return ResponseEntity.status(status.value()).body(responseBody);
    }
}
