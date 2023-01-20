package app.model;

import java.sql.Date;

public class EnchereRechercheCriteria {
    private String keyWord;
    private Date date;
    private int idCategorie;
    private double prixMin;
    private double prixMax;
    private boolean statut;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
}
