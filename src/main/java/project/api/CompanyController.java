package project.api;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.domain.Company;
import project.domain.Material;
import project.service.CompanyService;
import project.service.MaterialService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private MaterialService materialService;

    public CompanyController(CompanyService companyService, MaterialService materialService) {
        this.companyService = companyService;
        this.materialService = materialService;
    }

    @GetMapping
    public String showCompanies(ModelMap modelMap){
        List<Company> companies = companyService.getCompanies();
        modelMap.put("companies", companies);
        return "companies";
    }

    @GetMapping("/{id}")
    public String getCompanyMaterialsByCompanyId(@PathVariable String id, ModelMap modelMap){
        int companyId = Integer.parseInt(id);
        List<Material> materials = materialService.getMaterialsByCompanyId(companyId);
        modelMap.put("materials", materials);
        return "companyMaterials";
    }
}
