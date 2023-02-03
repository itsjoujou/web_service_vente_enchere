package app.repository;

import app.model.Mise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MiseRepository extends JpaRepository<Mise, Integer> {
    List<Mise> findByIdUser(int id_user);

    Mise findFirstByIdEnchereAndIdUserOrderByMontantDesc(int iduser, int idEnchere);

    Mise findFirstByIdEnchereOrderByIdDesc(int idEnchere);

    List<Mise> findByIdEnchere(int idEnchere);

    @Query("SELECT m FROM Mise m WHERE m.idEnchere = :idEnchere AND m.id = (SELECT MAX(m2.id) FROM Mise m2 WHERE m2.idEnchere = :idEnchere)")
    Mise findMaxMiseByIdEnchere(@Param("idEnchere") int idEnchere);
}