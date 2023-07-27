package zad1;

import java.time.LocalDate;

public class Wizyta {
    private Lekarz lekarz;
    private Pacjent pacjent;
    private LocalDate dataWizyty;

    public Wizyta(Lekarz lekarz, Pacjent pacjent, LocalDate dataWizyty) {
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.dataWizyty = dataWizyty;
        this.lekarz.getListaWizyt().add(this);
        this.pacjent.getListaWizyt().add(this);
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public LocalDate getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(LocalDate dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    @Override
    public String toString() {
        return "Wizyta{" +
                ", dataWizyty=" + dataWizyty +
                '}';
    }
}
