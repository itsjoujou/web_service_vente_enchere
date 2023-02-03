package app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "v_etat_enchere")
public class EtatEnchere {
    @Id
    @Column(name = "id_enchere")
    private int idEnchere;

    @Column(name = "date_fin")
    private Timestamp dateFin;

    @ManyToOne(targetEntity = Categorie.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "enchere_actuelle", nullable = false)
    private double enchereActuelle;

    @Column(name = "caption", length = Integer.MAX_VALUE)
    private String caption;

    @Column(name = "commission")
    private float commission;

    @Transient
    private Image image;

    @Transient
    private String statut;

    public int getId() {
        return idEnchere;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public int getIdUser() {
        return idUser;
    }

    public double getEnchereActuelle() {
        return enchereActuelle;
    }

    public String getCaption() {
        return caption;
    }

    public float getCommission() {
        return commission;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getStatut() {
        return statut;
    }
}