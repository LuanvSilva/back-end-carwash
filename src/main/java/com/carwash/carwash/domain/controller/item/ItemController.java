package com.carwash.carwash.domain.Controller.item;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.domain.Dtos.item.ItemDTO;
import com.carwash.carwash.domain.Service.item.ItemService;
import com.carwash.carwash.util.exceptions.CustomException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
    
    private final ItemService itemService;

    @PostMapping("/createItem")
    public ResponseEntity<ItemDTO> createItem(@Validated @RequestBody ItemDTO itemDTO, HttpServletRequest request) {
        String empresaMoon = request.getHeader("empresa");
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDTO, Long.parseLong(empresaMoon)));
    }

    @GetMapping("/getItemById/{id}")
    public ResponseEntity<ItemDTO> getItemById(@RequestBody Long id) {

        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/getAllItem") 
    public ResponseEntity<List<ItemDTO>> getAllItems(@RequestBody ItemDTO itemDTO) {

        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping("/findItemsByEmpresaId")
    public ResponseEntity<List<ItemDTO>> findItemsByEmpresaId(@RequestBody Long id_empresa) {
    
        return ResponseEntity.ok(itemService.findItemsByEmpresaId(id_empresa));
    }

    @PutMapping("/updateItem")
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, HttpServletRequest request) {
        String empresaMoon = request.getHeader("empresa");
        ItemDTO updatedItem = itemService.updateItem(itemDTO, Long.parseLong(empresaMoon));
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deletado com sucesso");
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
