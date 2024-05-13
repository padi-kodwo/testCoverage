package com.padi.testcoverage.service;


import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import java.util.List;

public interface UserService {

  /**
   * finds all users
   * @return a list of users
   */
  List<User> findAll();

  /**
   * finds a user by an ID
   * @param id users id
   * @return the user has the id
   */
  User findById(String id);

  /**
   * creates a new user
   * @param createUserDto user details to create the use
   * @return a User that has been created
   */
  User create(CreateUserDto createUserDto);

  /**
   * Edit or Update a user details
   * @param updateUserDto user to be edited details
   * @return a user that has been edited
   */
  User update(UpdateUserDto updateUserDto);

  /**
   * Deletes a user
   * @param id user id to use to delete the user
   * @return a message of the deletion performed
   */
  String delete(String id);
}
