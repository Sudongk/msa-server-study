package com.example.menu.repository;

import com.example.menu.domain.entity.Menu;
import com.example.menu.domain.response.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select new com.example.menu.domain.response.MenuResponse(m.id, m.name, m.price) " +
            "from Menu m " +
            "where m.store.id = :storeId")
    Page<MenuResponse> findAllByStore(@Param("storeId") Long storeId, Pageable pageable);

    @Query("select new com.example.menu.domain.response.MenuResponse(m.id, m.name, m.price) " +
            "from Store s " +
            "left join Menu m on m.store.id = s.id " +
            "where s.id = :storeId and m.id in (:menuIds)")
    List<MenuResponse> findAllByStoreIdAndInMenuIds(@Param("storeId") Long storeId, @Param("menuIds") List<Long> menuIds);

}
