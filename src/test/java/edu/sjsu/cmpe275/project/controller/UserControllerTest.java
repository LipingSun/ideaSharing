package edu.sjsu.cmpe275.project.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Liping on 11/19/15.
 */
public class UserControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new UserController()).build();

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("user1"))
        //.andExpect(content().string("1"))
        //.andExpect(content().json(actualObj.toString()))
        //.andExpect(jsonPath("$", hasSize(2)))
        //.andExpect(jsonPath("$[0].id", is(1)))
        //.andExpect(jsonPath("$.street").value("12345 Horton Ave"))
        //.andExpect(jsonPath("$.fieldErrors", hasSize(2)))
        ;
    }
}