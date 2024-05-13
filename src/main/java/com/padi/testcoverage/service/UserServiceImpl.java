package com.padi.testcoverage.service;

import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import com.padi.testcoverage.error.OkException;
import com.padi.testcoverage.repo.UserRepo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Autowired
  public UserServiceImpl(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    userRepo.findAll().forEach(users::add);
    return users;
  }

  @Override
  public User findById(String id) {
    return userRepo.findById(id).orElseThrow(() ->
        new OkException(404, "User not found with " + id));
  }

  @Override
  public User create(CreateUserDto createUserDto) {
    User user = new User();
    user.setName(createUserDto.getName());
    user.setDateOfBirth(LocalDateTime.parse(createUserDto.getDateOfBirth()));
    return userRepo.save(user);
  }

  @Override
  public User update(UpdateUserDto updateUserDto) {
    User user = findById(updateUserDto.getId());
    user.setName(updateUserDto.getName());
    user.setDateOfBirth(LocalDateTime.parse(updateUserDto.getDateOfBirth()));
    return userRepo.save(user);
  }

  @Override
  public String delete(String id) {
    userRepo.deleteById(id);
    return "user deleted with id:: " + id;
  }
}
