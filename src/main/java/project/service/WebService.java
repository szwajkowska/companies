package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import project.domain.Company;
import project.domain.Material;
import project.domain.MaterialDetails;

import java.util.Arrays;
import java.util.List;

@Service
public class WebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);

    private RestTemplate restTemplate;

    private final String URL = "http://193.142.112.220:8337/";

    public WebService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Company> getCompanies() {
        return Arrays.asList(restTemplate.getForObject(URL + "companyList", Company[].class));
    }

    public List<Material> getMaterials() {
        List<Material> materials = Arrays.asList(restTemplate.getForObject(URL + "materialList",
                Material[].class));
        return materials;
    }

    public List<Material> getMaterialsByCompanyID(int id) {
        try {
            return Arrays.asList(restTemplate.getForObject(URL + "materialList?companyID={id}",
                    Material[].class, id));
        } catch (HttpClientErrorException e) {
            logger.error("error for id:" + id, e);
            return null;
        }
    }

    public MaterialDetails getMaterialDetailsByID(int id) {
        return restTemplate.getForObject(URL + "materialDetails?ID={id}",
                MaterialDetails.class, id);
    }
}
