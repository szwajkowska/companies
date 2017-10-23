package project.repository;

import org.springframework.stereotype.Service;
import project.domain.Company;
import project.domain.Material;
import project.domain.MaterialDetails;
import project.service.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebServiceRepository {

    private WebService webService;
    private List<Company> companies = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();
    private List<MaterialDetails> materialDetails = new ArrayList<>();


    public WebServiceRepository(WebService webService) {
        this.webService = webService;
    }

    public List<Company> getCompanies() {
        if (companies.isEmpty()) {
            List<Company> webCompanies = webService.getCompanies();
            companies = new ArrayList<>(webCompanies);
        }
        return companies;
    }

    public List<Material> getMaterials() {
        if (materials.isEmpty()) {
            List<Material> webMaterials = webService.getMaterials();
           materials = new ArrayList<>(webMaterials);
        }
        return materials;
    }

    public List<Material> getMaterialsByCompanyId(int id) {
        return getMaterials().stream()
                .filter(e -> e.getCompanyID() == id)
                .collect(Collectors.toList());
    }

    public MaterialDetails getMaterialDetailsById(int id) {
        MaterialDetails materialDetailsByID;
        if (materialDetails.isEmpty()) {
            materialDetailsByID = webService.getMaterialDetailsByID(id);
            materialDetails.add(materialDetailsByID);
            return materialDetailsByID;
        } else {
            List<MaterialDetails> collect = materialDetails.stream()
                    .filter(e -> e.getID() == id)
                    .collect(Collectors.toList());

            if (collect.isEmpty()) {
                materialDetailsByID = webService.getMaterialDetailsByID(id);
                materialDetails.add(materialDetailsByID);
                return materialDetailsByID;
            } else {
                MaterialDetails materialDetails = collect.get(0);
                return materialDetails;

            }

        }
    }

    public void restoreData() {
        for (int i = 0; i < materialDetails.size(); i++) {
            materialDetails.remove(i);
        }
    }
}
