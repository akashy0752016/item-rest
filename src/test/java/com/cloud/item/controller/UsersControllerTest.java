package com.cloud.item.controller;

import com.cloud.item.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.cloud.item.constant.Paths.USERS;
import static com.cloud.item.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @MockBean
    private UsersController usersController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {
        Users user = createUser();

        List<Users> users = Collections.singletonList(user);

        when(usersController.getAllUsers()).thenReturn(users);

        mockMvc.perform(get(VERSION + USERS + "all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())))
        ;
    }

    @Test
    void getUsersById() throws Exception {
        Users users = createUser();

        when(usersController.getUsersById(users.getId())).thenReturn(users);

        mockMvc.perform(get(VERSION + USERS + users.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(users.getName())));
    }

    @Test
    void removeUsersById() throws Exception {
        Users users = createUser();

        mockMvc.perform(delete(VERSION + USERS + users.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void setUserData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(createUser());

        mockMvc.perform(post(VERSION + USERS).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    private Users createUser() {
        Users users = new Users();
        users.setName("BigG");
        users.setAddress("Glandale road 4");
        users.setCity("Glandale");
        users.setState("California");
        users.setZipCode("AD2123");
        users.setCardType("master");
        users.setCardNumber("2864528645765429346");
        users.setCardExpirationMonth(6);
        users.setCardExpirationYear(2020);
        users.setCardNameOnCard("BigGInDaHouse");
        users.setFlightId(12);

        return users;
    }

}