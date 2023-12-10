package com.example.backend_challenge.util;

import com.example.backend_challenge.exception.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SystemUtil {
    public static  <T> T unwrapEntity(Optional<T> entity, Long identifier, String entityName) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(entityName,identifier);
    }

    public static String[] getNullPropertyNames(Object source) {
        Set<String> emptyNames = new HashSet<>();
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            if (src.getPropertyValue(pd.getName()) == null) {
                emptyNames.add(pd.getName());
            }
        }

        return emptyNames.toArray(new String[0]);
    }
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    public static PageRequest pagination(String sortField,String sortDirection,Integer pageNumber,Integer pageSize){
        sortField = sortField == null ? "id" : sortField;
        sortDirection =sortDirection ==null ?"asc":sortDirection.toLowerCase();
        Sort.Direction direction = sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        pageNumber = pageNumber == null ? 0 : pageNumber;
        pageSize = pageSize == null ? 10 : pageSize;
        return PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField));
    }

}
