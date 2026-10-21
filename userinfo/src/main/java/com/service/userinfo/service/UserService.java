package com.service.userinfo.service;

import com.service.userinfo.dto.UserDto;
import com.service.userinfo.entity.User;
import com.service.userinfo.mapper.UserMapper;
import com.service.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDto addUser(UserDto userDto) {
        User user = userRepo.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    /**
     * Retrieves a user by id.
     *
     * @param userId the unique identifier of the user
     * @return the matching UserDto, or null if no user exists with that id
     */
    public UserDto fetchUserById(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

}
