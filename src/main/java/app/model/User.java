package app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private int id;

    @Column(name = "id_type_user", nullable = false)
    private short idTypeUser;

    @Column(name = "username", nullable = false, length = 35)
    private String username;

    @Column(name = "password", nullable = false, length = 36)
    private String password;

    @Column(name = "solde", nullable = false)
    private double solde;

    @Column(name = "token", length = 36)
    private String token;

    @Column(name = "date_expiration_token")
    private Timestamp dateExpirationToken;

    @Column(name = "validite_token", nullable = false)
    private boolean validiteToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(short idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateExpirationToken() {
        return dateExpirationToken;
    }

    public void setDateExpirationToken(Timestamp dateExpirationToken) {
        this.dateExpirationToken = dateExpirationToken;
    }

    public boolean getValiditeToken() {
        return validiteToken;
    }

    public void setValiditeToken(boolean validiteToken) {
        this.validiteToken = validiteToken;
    }

}