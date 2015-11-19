package edu.sjsu.cmpe275.project.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by xiaotong on 11/19/15.
 */
public class IdeaControllerTest {

    private final MockMvc mockMvc = standaloneSetup(new IdeaController()).build();

    @Test
    public void testGetIdeas() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/users/1/ideas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Good Idea"));
    }

    @Test
    public void testCreateIdea() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("api/v1/users/1/ideas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Good Idea"));
    }

    @Test
    public void testGetIdeaById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/users/1/ideas/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Good Idea"));
    }

    @Test
    public void testUpdateIdeaById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("api/v1/users/1/ideas/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Good Idea"));
    }
}
