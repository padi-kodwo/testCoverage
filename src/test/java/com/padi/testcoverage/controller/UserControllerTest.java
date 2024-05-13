package com.padi.testcoverage.controller;


import static com.padi.testcoverage.GlobalConstants.TEST_USER_ID;
import static org.mockito.ArgumentMatchers.any;

import com.padi.testcoverage.GlobalConstants;
import com.padi.testcoverage.dto.ApiResponse;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import com.padi.testcoverage.dto.UserDto;
import com.padi.testcoverage.enums.ResponseMessage;
import com.padi.testcoverage.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

class UserControllerTest {

  private final UserService userService;

  @InjectMocks
  private final UserController userController;

  UserControllerTest() {

    this.userService = Mockito.mock(UserService.class);
    ModelMapper modelMapper = Mockito.spy(ModelMapper.class);

    this.userController = new UserController(userService, modelMapper);
  }


  @BeforeEach
  void setUp() {
    Mockito.when(userService.create(any(CreateUserDto.class))).thenReturn(GlobalConstants.getUser1());
    Mockito.when(userService.update(any(UpdateUserDto.class))).thenReturn(GlobalConstants.getUser1());
    Mockito.when(userService.findById(TEST_USER_ID)).thenReturn(GlobalConstants.getUser1());
    Mockito.when(userService.findAll()).thenReturn(List.of(GlobalConstants.getUser1(), GlobalConstants.getUser2()));
    Mockito.when(userService.delete(TEST_USER_ID)).thenReturn("MSG");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void all() {

    ApiResponse<ArrayList<UserDto>> actual = userController.all();

    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals( ResponseMessage.SUCCESS.getMessage(),actual.getMessage());
    Assertions.assertEquals(2, actual.getData().size());
  }

  @Test
  void get() {
    ApiResponse<UserDto> actual = userController.get(TEST_USER_ID);

    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals( ResponseMessage.SUCCESS.getMessage(),actual.getMessage());
    Assertions.assertEquals(GlobalConstants.getUserDto1(), actual.getData());
  }

  @Test
  void create() {
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setDateOfBirth(GlobalConstants.TEST_USER_1_DOB);
    createUserDto.setName(GlobalConstants.TEST_USER_1_NAME);

    ApiResponse<UserDto> actual = userController.create(createUserDto);

    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals( ResponseMessage.SUCCESS.getMessage(),actual.getMessage());
    Assertions.assertEquals(GlobalConstants.getUserDto1(), actual.getData());
  }

  @Test
  void updated() {
    UpdateUserDto updateUserDto = new UpdateUserDto();
    updateUserDto.setDateOfBirth(GlobalConstants.TEST_USER_1_DOB);
    updateUserDto.setName(GlobalConstants.TEST_USER_1_NAME);
    updateUserDto.setId(GlobalConstants.TEST_USER_ID_1);

    ApiResponse<UserDto> actual = userController.update(updateUserDto);

    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals( ResponseMessage.SUCCESS.getMessage(),actual.getMessage());
    Assertions.assertEquals(GlobalConstants.getUserDto1(), actual.getData());

  }

  @Test
  void delete() {
    ApiResponse<String> actual = userController.delete(TEST_USER_ID);

    Assertions.assertEquals(actual.getCode(), ResponseMessage.SUCCESS.getCode());
    Assertions.assertEquals(actual.getMessage(), ResponseMessage.SUCCESS.getMessage());

    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals( ResponseMessage.SUCCESS.getMessage(),actual.getMessage());
    Assertions.assertEquals("MSG",actual.getData());
  }
}