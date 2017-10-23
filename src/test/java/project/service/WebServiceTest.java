package project.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import project.domain.Company;
import project.domain.Material;
import project.domain.MaterialDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(WebService.class)
public class WebServiceTest {

    @Autowired
    private WebService webService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URL = "http://193.142.112.220:8337/";

    @Before
    public void setUp() throws Exception{
        server= MockRestServiceServer.createServer(restTemplate);

    }

    public void webServiceTest(String url, Object value) throws Exception{
        String mapper = objectMapper.writeValueAsString(value);

        this.server.expect(requestTo(url))
                .andRespond(withSuccess(mapper, MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldGetMaterialDetailsByID() throws Exception{
        //given
        webServiceTest(URL + "materialDetails?ID=1", new MaterialDetails(1, "name", 2));
        //when
        MaterialDetails details = this.webService.getMaterialDetailsByID(1);
        //then
        Assertions.assertThat(details.getName()).isEqualTo("name");
    }


    @Test
    public void shouldGetMaterialsByCompanyID() throws Exception{
        //given
        webServiceTest(URL + "materialList?companyID=2",
                Arrays.asList(new Material(1, "x", 2), new Material(3, "y", 2)));
        //when
        List<Material> materialList = this.webService.getMaterialsByCompanyID(2);
        //then
        Assertions.assertThat(materialList.size()).isEqualTo(2);
        Assertions.assertThat(materialList.get(0).getName()).isEqualTo("x");
        Assertions.assertThat(materialList.get(1).getName()).isEqualTo("y");

    }

    @Test
    public void shouldGetMaterials() throws Exception{
        //given
        webServiceTest(URL + "materialList",
                Arrays.asList(new Material(1, "x", 2), new Material(3, "y", 2)));
        //when
        List<Material> materialList = this.webService.getMaterials();
        //then
        Assertions.assertThat(materialList.size()).isEqualTo(2);
        Assertions.assertThat(materialList.get(0).getName()).isEqualTo("x");
        Assertions.assertThat(materialList.get(1).getName()).isEqualTo("y");

    }

    @Test
    public void shouldGetCompanies() throws Exception{
        //given
        webServiceTest(URL + "companyList",
                Arrays.asList(new Company(1, "x"), new Company(3, "y")));
        //when
        List<Company> companyList = this.webService.getCompanies();
        //then
        Assertions.assertThat(companyList.size()).isEqualTo(2);
        Assertions.assertThat(companyList.get(0).getCompanyName()).isEqualTo("x");
        Assertions.assertThat(companyList.get(1).getCompanyName()).isEqualTo("y");

    }

//    @Test
//    public void shouldRestoreData() throws Exception{
//        //given
//        Material material1 = new Material(1, "x", 11);
//        Material material2 = new Material(2, "y", 11);
//        Material material3 = new Material(3, "z", 33);
//        List<Material> materials = new ArrayList<>();
//        materials.add(material1);
//        materials.add(material2);
//        materials.add(material3);
//        webServiceTest(URL + "materialList", Arrays.asList(material1, material2, material3));
//        //when
////        material1.setName("xyz");
//        List<Material> materialList = this.webService.getMaterials();
//
//        //then
//        Assertions.assertThat(materialList.get(0).getName()).isEqualTo("x");
//
//    }
}
