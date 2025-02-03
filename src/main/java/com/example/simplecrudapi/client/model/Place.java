package com.example.simplecrudapi.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Place {
    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String latitude;
    private String state;
}
