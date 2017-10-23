package project.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.Material;
import project.domain.MaterialDetails;
import project.service.MaterialService;

import java.util.List;

@Controller
@RequestMapping("/materials")
public class MaterialController {

    private MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public String showMaterials(ModelMap modelMap){
        List<Material> materials = materialService.getMaterials();
        modelMap.put("materials", materials);
        return "materials";
    }

    @GetMapping("/{id}")
    public String showMaterialDetailsByID(@PathVariable String id, ModelMap modelMap){
        int materialID = Integer.parseInt(id);
        MaterialDetails materialDetails = materialService.getMaterialDetailsByID(materialID);
        modelMap.put("materialDetails", materialDetails);
        return "materialDetails";
    }

    @PostMapping
    public void restoreData() {
        materialService.restoreData();
    }

    @PostMapping("/description/{id}")
    public String changeDescription(@PathVariable String id, @RequestParam String description){
        int materialId = Integer.parseInt(id);
        materialService.changeDescription(materialId, description);
        return "redirect:/materials/" + id + "?changedDescription";
    }

    @PostMapping("/notes/{id}")
    public String changeNotes(@PathVariable String id, @RequestParam String notes){
        int materialId = Integer.parseInt(id);
        materialService.changeNotes(materialId, notes);
        return "redirect:/materials/" + id + "?changedNotes";
    }

    @PostMapping("/supplier/{id}")
    public String changeSupplier(@PathVariable String id, @RequestParam String supplier){
        int materialId = Integer.parseInt(id);
        materialService.changeSupplier(materialId, supplier);
        return "redirect:/materials/" + id + "?changedSupplier";
    }

    @PostMapping("/price/{id}")
    public String changePrice(@PathVariable String id, @RequestParam String price){
        int materialPrice;
        try {
            materialPrice = Integer.parseInt(price);
        }catch (NumberFormatException e){
           return "redirect:/materials/" + id + "?price_error";
        }
        int materialId = Integer.parseInt(id);
        materialService.changePrice(materialId, materialPrice);
        return "redirect:/materials/" + id + "?changedPrice";
    }

    @PostMapping("/currency/{id}")
    public String changeCurrency(@PathVariable String id, @RequestParam String currency){
        int materialId = Integer.parseInt(id);
        materialService.changeCurrency(materialId, currency);
        return "redirect:/materials/" + id + "?changedCurrency";
    }
}
