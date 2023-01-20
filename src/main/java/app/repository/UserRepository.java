package app.repository;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM users WHERE username=?1 AND password=MD5(?2) AND id_type_user=?3", nativeQuery = true)
    Optional<User> authenticateUserWithUsernameAndHashedPassword(String username, String password, int typeUser);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET token=MD5(CONCAT(?1, CURRENT_TIMESTAMP)), validite_token=TRUE WHERE username=?1 AND password=MD5(?2)", nativeQuery = true)
    void generateToken(String username, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET solde=?1 WHERE id_user=?2", nativeQuery = true)
    void updateSoldeUser(double montant, int id_user);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users(username, password, id_type_user) VALUES (?1, MD5(?2), 2)", nativeQuery = true)
    void insertNewUser(String username, String password);
}