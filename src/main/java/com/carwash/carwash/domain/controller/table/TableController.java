package com.carwash.carwash.domain.Controller.table;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.domain.Service.table.TableService;
import com.carwash.carwash.util.exceptions.CustomException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/table")
public class TableController {

    private final TableService tableService;

    @GetMapping("/unidade")
    public ResponseEntity<?> getUnidade() {
        return ResponseEntity.ok(tableService.getUnidade());
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus() {
        return ResponseEntity.ok(tableService.getStatus());
    }

    @GetMapping("/tipoItem")
    public ResponseEntity<?> getTipo() {
        return ResponseEntity.ok(tableService.getTipo());
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> getCategoria() {
        return ResponseEntity.ok(tableService.getCategoria());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
