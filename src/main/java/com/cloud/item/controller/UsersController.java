package com.cloud.item.controller;

import com.cloud.item.model.Users;
import com.cloud.item.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.cloud.item.constant.Paths.USERS;
import static com.cloud.item.constant.Paths.VERSION;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = VERSION + USERS)
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "all", method = GET)
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = GET)
    @ResponseBody
    public Users getUsersById(@PathVariable(value = "id") int id) {
        return usersRepository.findAllById(id);
    }

    @RequestMapping(value = "{id}", method = DELETE)
    @ResponseBody
    public void removeUsersById(@PathVariable(value = "id") int id) {
        usersRepository.deleteById(id);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public void setUserData(@RequestBody Map<String, String> user) throws IOException {
        ObjectMapper om = new ObjectMapper();
        Users u = om.readValue(new ObjectMapper().writeValueAsString(user), Users.class);
        usersRepository.save(u);
    }
}
