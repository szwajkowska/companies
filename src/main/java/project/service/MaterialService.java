package project.service;

import org.springframework.stereotype.Service;
import project.domain.Material;
import project.domain.MaterialDetails;
import project.repository.WebServiceRepository;

import java.util.List;

@Service
public class MaterialService {

    private WebServiceRepository webServiceRepository;

    public MaterialService(WebServiceRepository webServiceRepository) {
        this.webServiceRepository = webServiceRepository;
    }

    public List<Material> getMaterialsByCompanyId(int id){
        return webServiceRepository.getMaterialsByCompanyId(id);
    }

    public MaterialDetails getMaterialDetailsByID(int id){
        return webServiceRepository.getMaterialDetailsById(id);
    }

    public List<Material> getMaterials(){
        return webServiceRepository.getMaterials();
    }


    public void changeDescription(int id, String description) {
        getMaterialDetailsByID(id).setDescription(description);
    }

    public void changeNotes(int id, String notes) {
        getMaterialDetailsByID(id).setNotes(notes);
    }

    public void changeSupplier(int id, String supplier) {
        getMaterialDetailsByID(id).setSupplier(supplier);
    }

    public void changePrice(int id, int price) {
        getMaterialDetailsByID(id).setPrice(price);
    }

    public void changeCurrency(int id, String currency) {
        getMaterialDetailsByID(id).setCurrency(currency);
    }

    public void restoreData(){
        webServiceRepository.restoreData();
    }
}
