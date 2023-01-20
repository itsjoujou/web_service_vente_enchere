package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @Column(name = "id_categorie", nullable = false)
    private Short id;

    @Column(name = "libelle_categorie", nullable = false, length = 50)
    private String libelleCategorie;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getLibelleCategorie() {
        return libelleCategorie;
    }

    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

}