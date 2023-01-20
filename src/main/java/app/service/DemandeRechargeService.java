package app.service;

import app.model.DemandeRecharge;
import app.repository.DemandeRechargeRepository;
import app.util.CustomError;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeRechargeService {
    @Autowired
    private final DemandeRechargeRepository demandeRechargeRepository;

    public DemandeRechargeService(DemandeRechargeRepository demandeRechargeRepository) {
        this.demandeRechargeRepository = demandeRechargeRepository;
    }

    public void save(DemandeRecharge demandeRecharge) {
        demandeRechargeRepository.save(demandeRecharge);
    }

    public Object getDemandesRecharge(int id_user) {
        List<DemandeRecharge> demandeRechargeList = demandeRechargeRepository.findDemandeRechargeByIdUser(id_user);

        return demandeRechargeList.isEmpty() ? new CustomError("Aucune demande effectuées.") : new Data(demandeRechargeList);
    }
}
