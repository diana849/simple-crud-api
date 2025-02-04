package com.example.simplecrudapi.client;

import com.example.simplecrudapi.client.model.ZipCodeData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "zip-code-client", url = "${client.zipcode.url}")
public interface ZipCodeClient {

    @GetMapping("/us/{zipcode}")
    Optional<ZipCodeData> getZipCodeInformation(@PathVariable String zipcode);
}
