package app.web;

import app.model.EnchereImage;
import app.model.EnchereRechercheCriteria;
import app.service.EnchereService;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RequestMapping("/encheres")
@RestController
public class EnchereController {
    @Autowired
    private final EnchereService enchereService;

    public EnchereController(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

    @GetMapping
    public Object allEncheresInProgress() {
        return enchereService.getEncheresInProgress();
    }

    @GetMapping("/search")
    public Object findEnchereByCriteria(
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyWord,
            @RequestParam(name = "id_categorie", required = false, defaultValue = "0") int id_categorie,
            @RequestParam(name = "prix_min", required = false, defaultValue = "0") double prix_min,
            @RequestParam(name = "prix_max", required = false, defaultValue = "0") double prix_max,
            @RequestParam(name = "statut", required = false, defaultValue = "false") boolean statut,
            @RequestParam(name = "date", required = false) Date date
    ) {
        EnchereRechercheCriteria criteria = new EnchereRechercheCriteria();
        criteria.setKeyWord(keyWord);
        criteria.setIdCategorie(id_categorie);
        criteria.setPrixMin(prix_min);
        criteria.setPrixMax(prix_max);
        criteria.setStatut(statut);
        criteria.setDate(date);

        return new Data(enchereService.findEnchereWithRechercheAvancee(criteria));
    }

    @GetMapping("/{id_enchere}")
    public Object getEnchere(@PathVariable int id_enchere) {
        return enchereService.getEnchere(id_enchere);
    }

    @GetMapping("/history/{id_user}")
    public Object getEnchereByIdUser(@PathVariable int id_user) {
        return enchereService.getEncheresByIdUser(id_user);
    }

    @PostMapping
    public void save(@RequestBody EnchereImage newEnchere) throws Exception {
        System.out.println(newEnchere.getImages().getBase64().length);
        enchereService.save(newEnchere);
    }
}
