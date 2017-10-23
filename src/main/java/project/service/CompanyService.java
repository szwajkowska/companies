package project.service;

import org.springframework.stereotype.Service;
import project.domain.Company;
import project.repository.WebServiceRepository;

import java.util.List;

@Service
public class CompanyService {

    private WebServiceRepository webServiceRepository;

    public CompanyService(WebServiceRepository webServiceRepository) {
        this.webServiceRepository = webServiceRepository;
    }

    public List<Company> getCompanies(){
        return webServiceRepository.getCompanies();
    }

}
