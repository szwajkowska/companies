package project.api;

import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest extends ControllerTest{

    @Test
    public void shouldShowLoginPage() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().isOk());
    }
}
