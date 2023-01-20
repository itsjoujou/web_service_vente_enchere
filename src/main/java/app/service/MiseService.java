package app.service;

import app.model.Mise;
import app.model.User;
import app.repository.MiseRepository;
import app.repository.UserRepository;
import app.util.CustomError;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiseService {
    @Autowired
    private final MiseRepository miseRepository;

    @Autowired
    private final UserRepository userRepository;

    public MiseService(MiseRepository miseRepository, UserRepository userRepository) {
        this.miseRepository = miseRepository;
        this.userRepository = userRepository;
    }

    public Object getMises(int id_user) {
        return new Data(miseRepository.findByIdUser(id_user));
    }

    @Transactional
    public void save(Mise newMise) throws Exception {
        User newUser = userRepository.findById(newMise.getIdUser()).get();

        if (newMise.getMontant() > newUser.getSolde()) {
            throw new Exception("Solde insuffisant!");
        }

        miseRepository.save(newMise);
        userRepository.updateSoldeUser(newUser.getSolde() + newMise.getMontant(), newUser.getId());
    }
}
