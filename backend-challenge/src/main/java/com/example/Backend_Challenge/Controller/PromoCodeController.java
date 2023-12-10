package com.example.backend_challenge.controller;

import com.example.backend_challenge.dto.PromoCodeDTO;
import com.example.backend_challenge.entity.PromoCodeEntity;
import com.example.backend_challenge.service.PromoCodeService;
import com.example.backend_challenge.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("promoCodes")
@Slf4j
public class PromoCodeController {
    private final PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeController(PromoCodeService promoCodeService) {
        this.promoCodeService = promoCodeService;
    }

    @SuppressWarnings("unused")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<PromoCodeEntity>>> getPromoCodes(@RequestParam(required = false)  String sortField,
                                                                         @RequestParam(required = false)  String sortDirection,
                                                                         @RequestParam(required = false)  Integer pageSize,
                                                                         @RequestParam(required = false)  Integer pageNumber,
                                                                         @RequestParam(required = false)String searchTerm){

        Page<PromoCodeEntity> result = promoCodeService.getAllPromoCodes(searchTerm,pageSize,pageNumber,sortField,sortDirection);
        ApiResponse<Page<PromoCodeEntity>> response = new ApiResponse<>("All promo codes are retrieved successfully",result, HttpStatus.OK);
        log.info("All promo codes are retrieved successfully");
        return  ResponseEntity.ok(response);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<PromoCodeEntity>> getPromoCodeById(@PathVariable Long id){

        PromoCodeEntity result = promoCodeService.getPromoCodeById(id);
        ApiResponse<PromoCodeEntity> response = new ApiResponse<>("Promo code with id "+id+" is retrieved successfully",result, HttpStatus.OK);
        log.info("Promo code with id "+id+" is retrieved successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @PostMapping
    public  ResponseEntity<ApiResponse<PromoCodeEntity>> createPromoCode(@Valid @RequestBody PromoCodeDTO promoCodeDTO){

        PromoCodeEntity result = promoCodeService.createPromoCode(promoCodeDTO);
        ApiResponse<PromoCodeEntity> response = new ApiResponse<>("Promo code is created successfully",result, HttpStatus.CREATED);
        log.info("Promo code is created successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @SuppressWarnings("unused")
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<PromoCodeEntity>> updatePromoCodeById(@PathVariable Long id,@RequestBody PromoCodeDTO promoCodeDTO){

        PromoCodeEntity result = promoCodeService.updatePromoCode(promoCodeDTO,id);
        ApiResponse<PromoCodeEntity> response = new ApiResponse<>("Promo code with id "+id+" is updated successfully",result, HttpStatus.OK);
        log.info("Promo code with id "+id+" is updated successfully");
        return  ResponseEntity.ok(response);
    }
    @SuppressWarnings("unused")
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<PromoCodeEntity>> deletePromoCodeById(@PathVariable Long id){

        promoCodeService.deletePromoCode(id);
        ApiResponse<PromoCodeEntity> response = new ApiResponse<>("Promo code with id "+id+" is deleted successfully",null, HttpStatus.OK);
        log.info("Promo code with id "+id+" is deleted successfully");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
