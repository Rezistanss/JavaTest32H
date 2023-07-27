package zad1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lekarz {
    private int id;
    private String nazwisko, imie, specjalizacja;
    private LocalDate dataUrodzenia;
    private String nip, pesel;
    private List<Wizyta> listaWizyt;

    public Lekarz(int id, String nazwisko, String imie, String specjalizacja, LocalDate dataUrodzenia, String nip, String pesel) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalizacja = specjalizacja;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
        this.listaWizyt = new ArrayList<>();
    }

    public List<Wizyta> getListaWizyt() {
        return listaWizyt;
    }

    public void setListaWizyt(List<Wizyta> listaWizyt) {
        this.listaWizyt = listaWizyt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "Lekarz{" +
                "id=" + id +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", specjalizacja='" + specjalizacja + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", nip='" + nip + '\'' +
                ", pesel='" + pesel + '\'' +
                ", iloscWizyt=" + listaWizyt.size() +
                '}';
    }
}
