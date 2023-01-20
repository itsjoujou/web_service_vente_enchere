package app.repository;

import app.model.Enchere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EnchereRepository extends JpaRepository<Enchere, Integer> {
    List<Enchere> findEnchereByDateFinAfter(Timestamp today);

    List<Enchere> findEnchereByIdUser(int id_user);
}
