package app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "duree_enchere")
public class DureeEnchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duree_enchere", nullable = false)
    private Short id;

    @Column(name = "duree_min", nullable = false)
    private double dureeMin;

    @Column(name = "duree_max", nullable = false)
    private double dureeMax;

    @Column(name = "date_debut", nullable = false)
    private Timestamp dateDebut;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public double getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(double dureeMin) {
        this.dureeMin = dureeMin;
    }

    public double getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(double dureeMax) {
        this.dureeMax = dureeMax;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

}