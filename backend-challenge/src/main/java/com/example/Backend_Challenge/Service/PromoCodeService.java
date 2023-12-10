package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.OrganizerDTO;
import com.example.backend_challenge.dto.PromoCodeDTO;
import com.example.backend_challenge.entity.OrganizerEntity;
import com.example.backend_challenge.entity.PromoCodeEntity;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface PromoCodeService {

    Page<PromoCodeEntity> getAllPromoCodes(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection);
    PromoCodeEntity getPromoCodeById(Long id);
    PromoCodeEntity getPromoCodeByCode(String code);

    PromoCodeEntity createPromoCode(PromoCodeDTO promoCode) ;

    PromoCodeEntity updatePromoCode(PromoCodeDTO promoCode,Long id);

    void deletePromoCode(Long id);
}
