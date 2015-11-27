package edu.sjsu.cmpe275.project.Idea_Test;

import edu.sjsu.cmpe275.project.rest.IdeaResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by xiaotong on 11/25/15.
 */
public class IdeaTest {
    private final MockMvc mockMvc = standaloneSetup(new IdeaResource()).build();


    @org.junit.Test
    public void testCreateIdeas() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("api/ideas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Fly Jacket"));
    }

//    public void testCreateUser() throws Exception {
//        mockMvc.perform(
//                post("/api/v1/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content("{\"username\":\"user1\",\"password\": \"passw\"," +
//                                "\"email\": \"user1@gmail.com\",\"description\": \"This is user1\"}"))
//                //.andExpect(status().isCreated())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("id").value("1"))
//                .andExpect(jsonPath("username").value("user1"))
//                .andExpect(jsonPath("email").value("user1@gmail.com"))
//                .andExpect(jsonPath("description").value("This is user1"));
//    }
}
