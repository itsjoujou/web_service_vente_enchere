package app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "mise")
public class Mise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mise", nullable = false)
    private int id;

    @Column(name = "id_enchere", nullable = false)
    private int idEnchere;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "montant", nullable = false)
    private double montant;

    @Column(name = "date_mise", columnDefinition = "CURRENT_TIMESTAMP")
    private Timestamp dateMise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Timestamp getDateMise() {
        return dateMise;
    }

    public void setDateMise(Timestamp dateMise) {
        this.dateMise = dateMise;
    }

}