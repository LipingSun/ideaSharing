package edu.sjsu.cmpe275.project.User_and_Idea_Test;

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

//    @org.junit.Test
//    public void testGetIdeas() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("api/users?"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("title").value("Good Idea"));
//    }
}
