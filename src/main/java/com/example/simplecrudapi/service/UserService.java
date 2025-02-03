package com.example.simplecrudapi.service;

import com.example.simplecrudapi.client.ZipCodeClient;
import com.example.simplecrudapi.client.model.ZipCodeData;
import com.example.simplecrudapi.dto.ExtendedUserDTO;
import com.example.simplecrudapi.dto.UserDTO;
import com.example.simplecrudapi.exception.NotFoundException;
import com.example.simplecrudapi.mapper.UserMapper;
import com.example.simplecrudapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements BaseService<UserDTO> {

    private UserRepository userRepository;

    private ZipCodeClient zipCodeClient;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        var userPage = userRepository.findAll(pageable);
        return userPage.map(UserMapper::toUserDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));
        return UserMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public ExtendedUserDTO getExtendedUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));

        var zipCodeData = zipCodeClient.getZipCodeInformation(user.getZipCode().replace("-", ""))
                .orElseGet(ZipCodeData::new);

        return UserMapper.toExtendedUserDTO(user, zipCodeData);
    }

    @Transactional
    public void updateUserById(Long id, String zipCode) {
        var user = userRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));
        user.setZipCode(zipCode);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        var user = userRepository.save(UserMapper.toUser(dto));
        return UserMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id: " + id));

        userRepository.delete(user);
    }
}
