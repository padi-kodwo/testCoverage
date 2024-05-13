package com.padi.testcoverage.controller;

import static com.padi.testcoverage.enums.ResponseMessage.FAILED;
import static com.padi.testcoverage.enums.ResponseMessage.SUCCESS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padi.testcoverage.domain.User;
import com.padi.testcoverage.dto.CreateUserDto;
import com.padi.testcoverage.dto.UpdateUserDto;
import com.padi.testcoverage.enums.ResponseMessage;
import com.padi.testcoverage.repo.UserRepo;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ObjectMapper objectMapper;

  private final List<User> savedUsers = new ArrayList<>();

  @BeforeEach
  void setUp() {
    prepareDB();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void givenUsers_whenGetAll_then200() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/users/all")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn();


    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(SUCCESS.getMessage());
  }

  @Test
  void givenUsers_whenGet_then200() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/users/" + savedUsers.getFirst().getId())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(SUCCESS.getMessage());
  }

  @Test
  void givenUsers_whenGet_then200AndNotExist() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/users/12234")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(FAILED.getMessage());
  }

  @Test
  void givenUsers_whenCreate_then200() throws Exception {
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setName("Test");
    createUserDto.setDateOfBirth("2000-06-08T13:22:47");

    MvcResult mvcResult = mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(createUserDto)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print()).andReturn();

    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(SUCCESS.getMessage());
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Test");
  }

  @Test
  void givenUsers_whenUpdate_then200() throws Exception{
    UpdateUserDto updateUserDto = new UpdateUserDto();
    updateUserDto.setId(savedUsers.getFirst().getId());
    updateUserDto.setName("Updated User");
    updateUserDto.setDateOfBirth("2000-05-08T13:22:47");

    MvcResult mvcResult = mockMvc.perform(put("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(updateUserDto)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print()).andReturn();

    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(SUCCESS.getMessage());
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Updated User");
  }

  @Test
  void givenUsers_whenDelete_then200() throws Exception{
    MvcResult mvcResult = mockMvc.perform(delete("/users/" + savedUsers.getFirst().getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn();

    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("data");
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains(SUCCESS.getMessage());
    assert mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("user delete");
  }

  private void prepareDB(){
    User user = new User();
    user.setName("Padi");
    user.setDateOfBirth(LocalDateTime.now());

    User user2 = new User();
    user2.setName("Amu");
    user2.setDateOfBirth(LocalDateTime.now());

    List<User> users = List.of(user2, user);

    userRepo.saveAll(users).forEach(savedUsers::add);
  }
}