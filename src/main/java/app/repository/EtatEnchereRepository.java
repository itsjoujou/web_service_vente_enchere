package app.repository;

import app.model.EtatEnchere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EtatEnchereRepository extends JpaRepository<EtatEnchere, Integer> {
    List<EtatEnchere> findEtatEncheresByDateFinAfter(Timestamp now);

    List<EtatEnchere> findByIdEnchere(int idEnchere);

    List<EtatEnchere> findByIdUser(int idEnchere);
}