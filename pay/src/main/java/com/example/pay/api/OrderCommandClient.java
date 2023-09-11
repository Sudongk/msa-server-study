package com.example.pay.api;

import com.example.pay.domain.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORDER-COMMAND-SERVICE")
public interface OrderCommandClient {

    @PostMapping
    void save(@RequestBody OrderRequest orderRequest);

}
