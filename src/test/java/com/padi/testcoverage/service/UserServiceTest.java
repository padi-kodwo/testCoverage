package com.padi.testcoverage.service;

import static com.padi.testcoverage.GlobalConstants.TEST_USER_1_DOB;
import static com.padi.testcoverage.GlobalConstants.TEST_USER_1_NAME;
import static com.padi.testcoverage.GlobalConstants.TEST_USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


import com.padi.testcoverage.GlobalConstants;
import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import com.padi.testcoverage.error.OkException;
import com.padi.testcoverage.repo.UserRepo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {

  @MockBean
  private UserRepo userRepo;

  @Autowired
  private UserService userService;

  @BeforeEach
  void setUp() {
    Mockito.when(userRepo.save(any(User.class))).thenReturn(GlobalConstants.getUser1());
    Mockito.when(userRepo.findById(TEST_USER_ID)).thenReturn(Optional.of(GlobalConstants.getUser1()));
    Mockito.when(userRepo.findById("11223344")).thenReturn(Optional.empty());
    Mockito.when(userRepo.findAll()).thenReturn(List.of(GlobalConstants.getUser1(), GlobalConstants.getUser2()));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findAll() {
    List<User> all = userService.findAll();

    assertNotNull(all);
    assertEquals(2, all.size());
  }

  @Test
  void findById() {
    User user = userService.findById(TEST_USER_ID);

    assertEquals(TEST_USER_1_NAME, user.getName());
  }

  @Test
  void findByIdThrowEx() {
    OkException assertedThrows = Assertions
        .assertThrows(OkException.class, () -> userService.findById("11223344"));

    assertTrue(assertedThrows.getMessage().contains("not found"));
  }

  @Test
  void create() {
    User user = userService.create(new CreateUserDto(TEST_USER_1_NAME, TEST_USER_1_DOB));

    Assertions.assertNotNull(user);
    Assertions.assertEquals(TEST_USER_1_NAME, user.getName());
  }

  @Test
  void edit() {
    User user = userService.update(new UpdateUserDto(TEST_USER_ID, TEST_USER_1_NAME, TEST_USER_1_DOB));

    Assertions.assertNotNull(user);
    Assertions.assertEquals(TEST_USER_1_NAME, user.getName());
  }

  @Test
  void delete() {
    String msg = userService.delete(TEST_USER_ID);
    assertEquals("user deleted with id:: " + TEST_USER_ID, msg);
  }

}