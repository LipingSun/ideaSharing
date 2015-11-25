package edu.sjsu.cmpe275.project.User_and_Idea_Test;

import edu.sjsu.cmpe275.project.rest.IdeaResource;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by xiaotong on 11/25/15.
 */
public class UserTest {
    private final MockMvc mockMvc = standaloneSetup(new IdeaResource()).build();

    @Test
    public void testGetIdeas() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("api/users?email=1234567@qq.com&password=123456&username=bunny"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("1234567@qq.com"));
    }


}
