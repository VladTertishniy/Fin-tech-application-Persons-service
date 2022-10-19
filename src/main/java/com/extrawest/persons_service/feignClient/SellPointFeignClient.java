package com.extrawest.persons_service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sell-point-application")
public interface SellPointFeignClient {
    @GetMapping("/sellPoints/isSellPointExist/{id}")
    ResponseEntity<Boolean> isSellPointExist(@PathVariable Long id);
}
