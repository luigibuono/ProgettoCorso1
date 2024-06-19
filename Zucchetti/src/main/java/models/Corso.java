package models;

public class Corso {
    private int idCorso;
    private String materia;
    private String nomeProfessore;
    private String cognomeProfessore;

    // Costruttore
    public Corso() {
    }

    // Metodi getter e setter per i campi
    public int getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNomeProfessore() {
        return nomeProfessore;
    }

    public void setNomeProfessore(String nomeProfessore) {
        this.nomeProfessore = nomeProfessore;
    }

    public String getCognomeProfessore() {
        return cognomeProfessore;
    }

    public void setCognomeProfessore(String cognomeProfessore) {
        this.cognomeProfessore = cognomeProfessore;
    }
}
