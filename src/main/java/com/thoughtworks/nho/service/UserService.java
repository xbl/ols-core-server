package com.thoughtworks.nho.service;

import com.thoughtworks.nho.dto.UserDTO;
import com.thoughtworks.nho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(user ->
                UserDTO.builder().id(user.getId()).name(user.getName())
                        .phoneNumber(user.getPhoneNumber()).build())
                .collect(Collectors.toList());
    }
}
