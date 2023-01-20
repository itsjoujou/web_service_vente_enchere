package app.repository;

import app.model.DemandeRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DemandeRechargeRepository extends JpaRepository<DemandeRecharge, Integer> {
    List<DemandeRecharge> findDemandeRechargeByIdUser(int id_user);
}