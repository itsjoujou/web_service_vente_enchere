package app.repository;

import app.model.Mise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiseRepository extends JpaRepository<Mise, Integer> {
    List<Mise> findByIdUser(int id_user);
}