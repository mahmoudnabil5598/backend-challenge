package com.example.backend_challenge.service;

import com.example.backend_challenge.dto.PromoCodeDTO;
import com.example.backend_challenge.entity.EventEntity;
import com.example.backend_challenge.entity.PromoCodeEntity;
import com.example.backend_challenge.repository.PromoCodeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.backend_challenge.util.SystemUtil.*;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {

    private final PromoCodeRepository promoCodeRepository;
    private final EventService eventService;

    private String entityName = "PromoCodeEntity";

    @Autowired
    public PromoCodeServiceImpl(PromoCodeRepository promoCodeRepository, EventService eventService) {
        this.promoCodeRepository = promoCodeRepository;
        this.eventService = eventService;
    }
    @Override
    public Page<PromoCodeEntity> getAllPromoCodes(String searchString, Integer pageSize, Integer pageNumber, String sortField, String sortDirection) {
        Pageable paginate= pagination(sortField,sortDirection,pageNumber,pageSize);
        searchString = searchString == null ? "" : searchString.toLowerCase();
        return promoCodeRepository.findByCodeContaining(searchString, paginate);
    }

    @Override
    public PromoCodeEntity getPromoCodeById(Long id) {
        return unwrapEntity(promoCodeRepository.findById(id), id, entityName);
    }

    @Override
    public PromoCodeEntity getPromoCodeByCode(String code) {
        return promoCodeRepository.findByCode(code);
    }

    @Override
    public PromoCodeEntity createPromoCode(PromoCodeDTO promoCode)  {
        //get  Event
        Set<EventEntity> events = promoCode.getEvents().stream()
                .map(eventService::getEventId)
                .collect(Collectors.toSet());

        //copy data
        PromoCodeEntity promoCodeObject = new PromoCodeEntity();
        BeanUtils.copyProperties(promoCode,promoCodeObject);

        promoCodeObject.setEvents(events);
        promoCodeObject.setInitialLimit(promoCodeObject.getUsageLimit());

        return promoCodeRepository.save(promoCodeObject);
    }

    @Override
    public PromoCodeEntity updatePromoCode(PromoCodeDTO promoCode, Long id) {


        PromoCodeEntity oldPromoCode = unwrapEntity(promoCodeRepository.findById(id), id, entityName);
Set<EventEntity>events = new HashSet<>();
if(promoCode.getEvents()!=null) {
    for (Long eventId : promoCode.getEvents()) {
        events.add(eventService.getEventId(eventId));
    }
    oldPromoCode.setEvents(events);
}


        copyNonNullProperties(promoCode, oldPromoCode);
        oldPromoCode.setInitialLimit(oldPromoCode.getUsageLimit());

        return promoCodeRepository.save(oldPromoCode);
    }

    @Override
    public void deletePromoCode(Long id) {
        promoCodeRepository.deleteById(id);

    }
}
