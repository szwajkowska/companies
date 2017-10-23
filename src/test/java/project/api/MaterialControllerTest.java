package project.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.domain.MaterialDetails;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MaterialControllerTest extends ControllerTest {


    @Test
    public void shouldShowMaterials() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/materials")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldShowMaterialDetailsByID() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

     given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/materials/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(model().attribute("materialDetails", is(
                        allOf(
                                hasProperty("ID", is(1)),
                                hasProperty("name", is("name")),
                                hasProperty("companyID", is(11)),
                                hasProperty("description", is("description")),
                                hasProperty("notes", is("notes")),
                                hasProperty("supplier", is("supplier")),
                                hasProperty("price", is(1)),
                                hasProperty("currency", is("EUR"))
                        )
                )));

    }

    @Test
    public void shouldChangeDescription() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");
        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/description/1")
                        .param("description", "xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertThat(materialDetails.getDescription()).isEqualTo("xxx");
    }

    @Test
    public void shouldChangeNotes() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/notes/1")
                        .param("notes", "xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertThat(materialDetails.getNotes()).isEqualTo("xxx");

    }

    @Test
    public void shouldChangeSupplier() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/supplier/1")
                        .param("supplier", "xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection());

        Assertions.assertThat(materialDetails.getSupplier()).isEqualTo("xxx");
    }

    @Test
    public void shouldChangeCurrency() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/currency/1")
                        .param("currency", "xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertThat(materialDetails.getCurrency()).isEqualTo("xxx");
    }

    @Test
    public void shouldChangePrice() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/price/1")
                        .param("price", "22")

                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertThat(materialDetails.getPrice()).isEqualTo(22);
    }


    @Test
    public void shouldNotChangePrice() throws Exception {

        MaterialDetails materialDetails = new MaterialDetails(1, "name", 11, "description", "notes",
                "supplier", 1, "EUR");

        given(this.webService.getMaterialDetailsByID(1)).willReturn(materialDetails);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/materials/price/1")
                        .param("price", "xxx")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertThat(materialDetails.getPrice()).isEqualTo(1);
    }
}



