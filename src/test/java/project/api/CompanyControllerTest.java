package project.api;

import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.domain.Material;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CompanyControllerTest extends ControllerTest{

    @Test
    public void shouldShowCompanies() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/companies")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetMaterialsByCompanyID() throws Exception {
        Material material1 = new Material(1, "x", 11);
        Material material2 = new Material(2, "y", 11);
        Material material3 = new Material(3, "z", 33);
        List<Material> materials = new ArrayList<>();
        materials.add(material1);
        materials.add(material2);
        materials.add(material3);

        given(this.webService.getMaterials()).willReturn(materials);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/companies/11")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(model().attribute("materials", hasSize(2)))
                .andExpect(model().attribute("materials", hasItem(
                        allOf(
                                hasProperty("ID", is(1)),
                                hasProperty("name", is("x")),
                                hasProperty("companyID", is(11))
                        )
                )))
                .andExpect(model().attribute("materials", hasItem(
                        allOf(
                                hasProperty("ID", is(2)),
                                hasProperty("name", is("y")),
                                hasProperty("companyID", is(11))
                        )
                )));
    }
}
