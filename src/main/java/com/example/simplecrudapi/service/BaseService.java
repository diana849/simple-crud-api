package com.example.simplecrudapi.service;

import com.example.simplecrudapi.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T extends BaseDTO> {

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    T save(T dto);

    void delete(Long id);
}
