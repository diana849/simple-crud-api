package com.example.simplecrudapi.client;

import com.example.simplecrudapi.client.model.ZipCodeData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange
public interface ZipCodeClient {

    @GetExchange("/us/{zipcode}")
    Optional<ZipCodeData> getZipCodeInformation(@PathVariable String zipcode);
}
