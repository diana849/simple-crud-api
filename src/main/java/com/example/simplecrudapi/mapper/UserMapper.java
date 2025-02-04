package com.example.simplecrudapi.mapper;

import com.example.simplecrudapi.client.model.Place;
import com.example.simplecrudapi.client.model.ZipCodeData;
import com.example.simplecrudapi.dto.ExtendedUserDTO;
import com.example.simplecrudapi.dto.PlaceDTO;
import com.example.simplecrudapi.dto.UserDTO;
import com.example.simplecrudapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    ExtendedUserDTO toExtendedUserDTO(User user, ZipCodeData zipCodeData);

    User toUser(UserDTO userDTO);

    PlaceDTO toPlaceDto(Place place);
}
