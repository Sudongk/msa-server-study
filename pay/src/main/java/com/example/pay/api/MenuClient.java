package com.example.pay.api;

import com.example.pay.domain.response.MenuResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MENU-SERVICE")
public interface MenuClient {

    @GetMapping("/api/v1/menu/store/{id}/menus")
    ResponseEntity<List<MenuResponse>> findAllByStoreIdAndInMenuIds(
            @PathVariable("id") Long storeId,
            @RequestParam("menuIds") String menus
    );

}
