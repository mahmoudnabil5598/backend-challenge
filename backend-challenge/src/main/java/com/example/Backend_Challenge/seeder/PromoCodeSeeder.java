package com.example.backend_challenge.seeder;

import com.example.backend_challenge.dto.LocationDTO;
import com.example.backend_challenge.dto.PromoCodeDTO;
import com.example.backend_challenge.entity.PromoCodeEntity;
import com.example.backend_challenge.repository.EventRepository;
import com.example.backend_challenge.repository.PromoCodeRepository;
import com.example.backend_challenge.service.EventService;
import com.example.backend_challenge.service.PromoCodeService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@DependsOn({"eventSeeder"})
public class PromoCodeSeeder {

    private final PromoCodeService promoCodeService;

    private final PromoCodeRepository promoCodeRepository;


    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public PromoCodeSeeder(PromoCodeService promoCodeService, PromoCodeRepository promoCodeRepository, EntityManager entityManager) {
        this.promoCodeService = promoCodeService;
        this.promoCodeRepository = promoCodeRepository;
        this.entityManager = entityManager;

    }
    @PostConstruct
    @Transactional
    public void promoCodeRepository() {
        if (promoCodeRepository.count() == 0) {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime after5Days = today.plusDays(5);
            LocalDateTime before5Days = today.minusDays(5);
            LocalDateTime before10Days = today.minusDays(10);
            Set<Long> event = new HashSet<>();
            event.add(1L);
            event.add(2L);
            event.add(3L);
            // create 5 Locations
            PromoCodeDTO promoCode1 = new PromoCodeDTO(today,after5Days,10,50,"APX-1",event);
            PromoCodeDTO promoCode2 =  new PromoCodeDTO(today,after5Days,20,60,"APX-2",event);
            PromoCodeDTO promoCode3 =  new PromoCodeDTO(today,after5Days,30,70,"APX-3",event);
            PromoCodeDTO promoCode4 =  new PromoCodeDTO(today,after5Days,40,80,"APX-4",event);
            PromoCodeDTO promoCode5 =  new PromoCodeDTO(before10Days,before5Days,50,10,"APX-5",event);


            // save lo to database
            promoCodeService.createPromoCode(promoCode1);
            promoCodeService.createPromoCode(promoCode2);
            promoCodeService.createPromoCode(promoCode3);
            promoCodeService.createPromoCode(promoCode4);
            promoCodeService.createPromoCode(promoCode5);

        }
    }
}
