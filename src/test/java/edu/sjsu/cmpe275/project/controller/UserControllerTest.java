package edu.sjsu.cmpe275.project.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Liping on 11/19/15.
 */
public class UserControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new UserController()).build();

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(
                post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user1\",\"password\": \"passw\"," +
                                "\"email\": \"user1@gmail.com\",\"description\": \"This is user1\"}"))
                //.andExpect(status().isCreated())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("username").value("user1"))
                .andExpect(jsonPath("email").value("user1@gmail.com"))
                .andExpect(jsonPath("description").value("This is user1"));
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(
                get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("user1"));
        //.andExpect(content().string("1"))
        //.andExpect(content().json(actualObj.toString()))
        //.andExpect(jsonPath("$", hasSize(2)))
        //.andExpect(jsonPath("$[0].id", is(1)))
        //.andExpect(jsonPath("$.street").value("12345 Horton Ave"))
        //.andExpect(jsonPath("$.fieldErrors", hasSize(2)))

    }

    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(
                post("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"user1 is awesome\"}"))
                .andExpect(jsonPath("description").value("user1 is awesome"));
    }
}