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
        return userRepository.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            return userDTO;
        }).collect(Collectors.toList());
    }
}
