package project.repository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import project.domain.Company;
import project.domain.Material;
import project.domain.MaterialDetails;
import project.service.WebService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebServiceRepositoryTest {

    @Mock
    private WebServiceRepository webServiceRepository;

    @Mock
    private WebService webService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        webServiceRepository = new WebServiceRepository(webService);
    }

    @Test
    public void shouldGetCompanies() {
        //given
        Company company1 = new Company(1, "x");
        Company company2 = new Company(2, "y");
        Mockito.when(webService.getCompanies()).thenReturn(Arrays.asList(company1, company2));

        //when
        List<Company> companies = webServiceRepository.getCompanies();
        //then
        Assertions.assertThat(companies.size()).isEqualTo(2);
        Assertions.assertThat(companies.get(0).getCompanyName()).isEqualTo("x");
    }

    @Test
    public void shouldGetMaterials() {
        //given
        Material material1 = new Material(1, "x", 1);
        Material material2 = new Material(2, "y", 2);
        Mockito.when(webService.getMaterials()).thenReturn(Arrays.asList(material1, material2));

        //when
        List<Material> materials = webServiceRepository.getMaterials();
        //then
        Assertions.assertThat(materials.size()).isEqualTo(2);
        Assertions.assertThat(materials.get(0).getName()).isEqualTo("x");
    }

    @Test
    public void shouldGetMaterialsByCompanyID(){
        //given
        Material material1 = new Material(1, "x", 11);
        Material material2 = new Material(2, "y", 22);
        Material material3 = new Material(3, "z", 33);
        Material material4 = new Material(4, "w", 33);
        Mockito.when(webService.getMaterials())
                .thenReturn(Arrays.asList(material1, material2, material3, material4));
        //when
        List<Material> materialsByCompanyId = webServiceRepository.getMaterialsByCompanyId(33);
        //then
        Assertions.assertThat(materialsByCompanyId.size()).isEqualTo(2);
        Assertions.assertThat(materialsByCompanyId.get(0).getName()).isEqualTo("z");

    }

    @Test
    public void shouldGetMaterialDetailsByID(){
        //given
        MaterialDetails materialDetails1 = new MaterialDetails(1, "x", 11
                , "description1", "notes1", "supplier1", 111, "EUR"
        );
        MaterialDetails materialDetails2 = new MaterialDetails(2, "y", 22
                , "description2", "notes2", "supplier2", 222, "ZL"
        );
        Mockito.when(webService.getMaterialDetailsByID(2)).thenReturn(materialDetails2);
        //when
        MaterialDetails materialDetailsById = webServiceRepository.getMaterialDetailsById(2);
        //then
        Assertions.assertThat(materialDetailsById.getName()).isEqualTo("y");
        Assertions.assertThat(materialDetails2.getDescription()).isEqualTo("description2");

    }

//    @Test
//    public void shouldRestoreData() {
//
//        //given
//        Material material1 = new Material(1, "x", 11);
//        Material material2 = new Material(2, "y", 11);
//        Material material3 = new Material(3, "z", 33);
//        List<Material> materials = new ArrayList<>();
//        materials.add(material1);
//        materials.add(material2);
//        materials.add(material3);
//
//        Mockito.when(webService.getMaterials()).thenReturn(materials);
//        List<Material> materialList = webService.getMaterials();
//        materialList.get(0).setName("xxx");
//
//        Assertions.assertThat(webService.getMaterials().get(0).getName()).isEqualTo("xxx");
//
//        webServiceRepository.restoreData();
//
//        Assertions.assertThat(webService.getMaterials().get(0).getName()).isEqualTo("x");

//
//    }

}

