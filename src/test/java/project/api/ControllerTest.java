package project.api;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project.Application;
import project.domain.Material;
import project.domain.MaterialDetails;
import project.service.WebService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public abstract class ControllerTest {


    @Autowired
    WebApplicationContext ctx;

    MockMvc mockMvc;

    @MockBean
    WebService webService;

    BDDMockito.BDDMyOngoingStubbing<MaterialDetails> stubbing;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear(){
//        for (int i = 0; i<materials.size(); i++) {
//            webService.getMaterialDetailsByID(materials.get(i).getID()).equals(null);
//        }
    }
}
