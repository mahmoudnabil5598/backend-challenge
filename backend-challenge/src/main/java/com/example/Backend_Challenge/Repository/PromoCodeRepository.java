package com.example.backend_challenge.repository;

import com.example.backend_challenge.entity.PromoCodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromoCodeRepository extends JpaRepository<PromoCodeEntity,Long> {

    Page<PromoCodeEntity> findByCodeContaining(String searchString, Pageable paginate);

    PromoCodeEntity findByCode(String code);
}
