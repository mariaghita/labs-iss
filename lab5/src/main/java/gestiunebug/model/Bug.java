package gestiunebug.model;

public class Bug {
    private int codBug;
    private String denumire, descriere;
    private User verificator;
    private StatusBug status;

    public Bug(String denumire, String descriere, User verificator) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.verificator = verificator;
    }

    public int getCodBug() {
        return codBug;
    }

    public void setCodBug(int codBug) {
        this.codBug = codBug;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public User getVerificator() {
        return verificator;
    }

    public void setVerificator(User verificator) {
        this.verificator = verificator;
    }

    public StatusBug getStatus() {
        return status;
    }

    public void setStatus(StatusBug status) {
        this.status = status;
    }
}
