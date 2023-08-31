package com.example.menu.service;

import com.example.menu.domain.request.MenuRequest;
import com.example.menu.domain.response.MenuResponse;
import com.example.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void save(MenuRequest request) {
        menuRepository.save(request.toEntity());
    }

    public Page<MenuResponse> findAllByStoreId(Long storeId, Pageable pageable) {
        return menuRepository.findAllByStore(
                storeId,
                pageable
        );
    }

}
