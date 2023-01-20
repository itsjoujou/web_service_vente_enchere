package app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "commission")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commission", nullable = false)
    private Short id;

    @Column(name = "pourcentage", nullable = false)
    private double pourcentage;

    @Column(name = "date_debut", nullable = false)
    private Timestamp dateDebut;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

}