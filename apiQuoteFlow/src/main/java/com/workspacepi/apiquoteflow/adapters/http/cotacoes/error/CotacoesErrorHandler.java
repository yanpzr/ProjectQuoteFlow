package com.workspacepi.apiquoteflow.adapters.http.cotacoes.error;

import com.workspacepi.apiquoteflow.application.cotacoes.exceptions.CotacaoNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class CotacoesErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CotacoesErrorHandler.class);

    // Lida com a empresaNaoEncontradaException
    @ExceptionHandler(CotacaoNaoEncontradaException.class)
    public ResponseEntity<Object> handleCotacaoNaoEncontradaException(CotacaoNaoEncontradaException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Método genérico para outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CotacoesErrorResponse> handleException(Exception e) {
        return buildResponse(null, HttpStatus.BAD_REQUEST, "Erro desconhecido", e);
    }

    // Método auxiliar para construir a resposta
    private ResponseEntity<CotacoesErrorResponse> buildResponse(UUID id, HttpStatus status, String message, Throwable throwable) {
        CotacoesErrorResponse response = new CotacoesErrorResponse(id, message);
        String fullMessage = "[" + status.value() + "] [" + throwable.getClass().getSimpleName() + "] " + message;

        if (status.is5xxServerError()) {
            LOGGER.error(fullMessage, throwable);
        } else {
            LOGGER.warn(fullMessage);
        }

        return ResponseEntity.status(status).body(response);
    }
}
