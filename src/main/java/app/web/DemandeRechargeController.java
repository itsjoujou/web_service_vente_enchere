package app.web;

import app.model.DemandeRecharge;
import app.service.DemandeRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/demandes_recharge")
@RestController
public class DemandeRechargeController {
    @Autowired
    public final DemandeRechargeService demandeRechargeService;

    public DemandeRechargeController(DemandeRechargeService demandeRechargeService) {
        this.demandeRechargeService = demandeRechargeService;
    }

    @GetMapping("/user/{id_user}")
    public Object all(@PathVariable int id_user) {
        return demandeRechargeService.getDemandesRecharge(id_user);
    }

    @PostMapping
    public void save(@RequestBody DemandeRecharge newDemandeRecharge) {
        demandeRechargeService.save(newDemandeRecharge);
    }
}
