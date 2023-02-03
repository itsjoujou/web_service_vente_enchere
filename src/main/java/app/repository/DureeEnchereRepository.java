package app.repository;

import app.model.DureeEnchere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface DureeEnchereRepository extends JpaRepository<DureeEnchere, Short> {
    Optional<DureeEnchere> findFirstByDateDebutBeforeOrderByDateDebutDesc(Timestamp now);
}