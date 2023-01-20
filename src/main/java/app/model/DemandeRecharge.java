package app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "demande_recharge")
public class DemandeRecharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande", nullable = false)
    private int id;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "montant", nullable = false)
    private double montant;

    @Column(name = "date_validation")
    private Timestamp dateValidation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Timestamp dateValidation) {
        this.dateValidation = dateValidation;
    }

}