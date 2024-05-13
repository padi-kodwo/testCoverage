package com.padi.testcoverage;

import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.UserDto;
import java.time.LocalDateTime;

public class GlobalConstants {

  public static String TEST_STRING_1 = "String_1";
  public static String TEST_USER_ID = "TEST_ID";
  public static String TEST_USER_ID_1 = "TEST_USER_ID_1";
  public static String TEST_USER_ID_2 = "TEST_USER_ID_2";
  public static String TEST_USER_1_NAME = "Kwame";
  public static String TEST_USER_1_DOB = "2000-06-08T13:22:47";
  public static String TEST_USER_2_DOB = "2001-06-08T13:22:47";

  public static  User getUser1(){
    User user = new User();
    user.setName(TEST_USER_1_NAME);
    user.setDateOfBirth(LocalDateTime.parse(TEST_USER_1_DOB));
    return user;
  }

  public static  User getUser2(){
    User user = new User();
    user.setName("Kofi");
    user.setDateOfBirth(LocalDateTime.parse(TEST_USER_2_DOB));
    return user;
  }

  public static  UserDto getUserDto1(){
    UserDto user = new UserDto();
    user.setName(TEST_USER_1_NAME);
    user.setDateOfBirth(TEST_USER_1_DOB);
    return user;
  }

  public static  UserDto getUserDto2(){
    UserDto user = new UserDto();
    user.setName("Kofi");
    user.setDateOfBirth(TEST_USER_2_DOB);
    return user;
  }


}
