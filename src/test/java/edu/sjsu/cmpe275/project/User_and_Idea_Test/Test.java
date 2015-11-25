package edu.sjsu.cmpe275.project.User_and_Idea_Test;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by xiaotong on 11/25/15.
 */
public class Test {
    public void testGetIdeas() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/users/1/ideas"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("title").value("Good Idea"));
    }
}
