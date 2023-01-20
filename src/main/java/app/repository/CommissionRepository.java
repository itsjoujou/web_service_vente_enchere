package app.repository;

import app.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

public interface CommissionRepository extends JpaRepository<Commission, Short> {
    @Transactional
    @Query(value = "SELECT * FROM commission ORDER BY date_debut DESC LIMIT 1", nativeQuery = true)
    Optional<Commission> findCurrentCommission();
}