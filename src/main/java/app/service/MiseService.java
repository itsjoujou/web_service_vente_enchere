package app.service;

import app.model.Enchere;
import app.model.Mise;
import app.model.User;
import app.repository.EnchereRepository;
import app.repository.MiseRepository;
import app.repository.UserRepository;
import app.util.CustomError;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MiseService {
    @Autowired
    private final MiseRepository miseRepository;

    @Autowired
    private final UserRepository userRepository;
    private final EnchereRepository enchereRepository;

    public MiseService(MiseRepository miseRepository, UserRepository userRepository,
                       EnchereRepository enchereRepository) {
        this.miseRepository = miseRepository;
        this.userRepository = userRepository;
        this.enchereRepository = enchereRepository;
    }

    public Object getMises(int id_user) {
        return new Data(miseRepository.findByIdUser(id_user));
    }

    @Transactional
    public void save(Mise newMise) throws Exception {
        newMise.setDateMise(new Timestamp(System.currentTimeMillis()));
        User newUser = userRepository.findById(newMise.getIdUser()).get();
        Mise lastMiseEnchere = miseRepository.findMaxMiseByIdEnchere(newMise.getIdEnchere());
        Enchere myEnchere = enchereRepository.findById(newMise.getIdEnchere()).get();
        List<Mise> getByIdEnchere = miseRepository.findByIdEnchere(newMise.getIdEnchere());

        if (newMise.getMontant() > newUser.getSolde()) {
            throw new Exception("Solde insuffisant!");
        }

        if(myEnchere!=null) {
            if(myEnchere.getIdUser()==newMise.getIdUser() && myEnchere.getId()==newMise.getIdEnchere()) {
                throw new Exception("vous ne pouvez pas miser sur votre propre enchere");
            }
        }

        if(lastMiseEnchere!=null) {
            if(lastMiseEnchere.getIdUser()==newMise.getIdUser()) {
                throw new Exception("Vous êtes le dernier qui avait miser");
            }
        }

        if(lastMiseEnchere!=null) {
            if (lastMiseEnchere.getIdUser() != newMise.getIdUser() && newMise.getMontant() < lastMiseEnchere.getMontant()) {
                throw new Exception("Vueillez augmenter le montant");
            }
        }

        if(getByIdEnchere.size()==0) {
            if(newMise.getMontant()< myEnchere.getMontantDebutEnchere()) {
                //System.out.println("DDDDDDDDD");
                throw new Exception("le montant debut est de " + myEnchere.getMontantDebutEnchere());
            }
        }

        miseRepository.save(newMise);
        userRepository.updateSoldeUser(newUser.getSolde() - newMise.getMontant(), newUser.getId());
    }
}
