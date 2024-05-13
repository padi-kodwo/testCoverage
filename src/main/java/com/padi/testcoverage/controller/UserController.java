package com.padi.testcoverage.controller;


import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.ApiResponse;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import com.padi.testcoverage.dto.UserDto;
import com.padi.testcoverage.service.UserService;
import com.padi.testcoverage.util.ResponseUtil;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("all")
  public ApiResponse<ArrayList<UserDto>> all(){
    logger.info("STARTED ::: all");

    List<User> all = userService.findAll();
    ArrayList<UserDto> dtos = all.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toCollection(ArrayList::new));

    ApiResponse<ArrayList<UserDto>> apiResponse = ResponseUtil.wrapInApiResponse(dtos);

    logger.info("DONE ::: all {}", apiResponse);

    return apiResponse;
  }

  @GetMapping("{id}")
  public ApiResponse<UserDto> get(@PathVariable String id){
    logger.info("STARTED ::: get {}", id);

    User user = userService.findById(id);
    UserDto dto = modelMapper.map(user, UserDto.class);
    ApiResponse<UserDto> apiResponse = ResponseUtil.wrapInApiResponse(dto);

    logger.info("DONE ::: get {}", apiResponse);

    return apiResponse;
  }

  @PostMapping
  public ApiResponse<UserDto> create(@Valid @RequestBody CreateUserDto createUserDto){
    logger.info("STARTED ::: create {}", createUserDto);

    User user = userService.create(createUserDto);
    UserDto dto = modelMapper.map(user, UserDto.class);
    ApiResponse<UserDto> apiResponse = ResponseUtil.wrapInApiResponse(dto);

    logger.info("DONE ::: create {}", apiResponse);

    return apiResponse;
  }

  @PutMapping
  public ApiResponse<UserDto> update(@Valid @RequestBody UpdateUserDto updateUserDto){
    logger.info("STARTED ::: updated {}", updateUserDto);

    User user = userService.update(updateUserDto);
    UserDto dto = modelMapper.map(user, UserDto.class);
    ApiResponse<UserDto> apiResponse = ResponseUtil.wrapInApiResponse(dto);

    logger.info("DONE ::: updated {}", apiResponse);

    return apiResponse;
  }


  @DeleteMapping("{id}")
  public ApiResponse<String> delete(@PathVariable String id){
    logger.info("STARTED ::: delete {}", id);

    String msg = userService.delete(id);
    ApiResponse<String> apiResponse = ResponseUtil.wrapInApiResponse(msg);

    logger.info("DONE ::: delete {}", apiResponse);

    return apiResponse;
  }
}
