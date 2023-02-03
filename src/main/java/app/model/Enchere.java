package app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "enchere")
public class Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enchere", nullable = false)
    private int id;

    @Column(name = "date_debut", nullable = false, columnDefinition = "CURRENT_TIMESTAMP")
    private Timestamp dateDebut;

    @Column(name = "date_fin", nullable = false)
    private Timestamp dateFin;

    @ManyToOne(targetEntity = Categorie.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "montant_debut_enchere", nullable = false)
    private double montantDebutEnchere;

    @Column(name = "caption", length = Integer.MAX_VALUE)
    private String caption;

    @Column(name = "commission", nullable = false)
    private double commission;

    @Transient
    private long duree;

    @Transient
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getMontantDebutEnchere() {
        return montantDebutEnchere;
    }

    public void setMontantDebutEnchere(double montantDebutEnchere) {
        this.montantDebutEnchere = montantDebutEnchere;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public long getDuree() {
        return duree;
    }

    public void setDuree(long duree) {
        this.duree = duree;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}