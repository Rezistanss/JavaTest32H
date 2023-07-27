package zad1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pacjent {
    private int id;
    private String nazwisko, imie, pesel;
    private LocalDate dataUrodzenia;
    private List<Wizyta> listaWizyt;

    public Pacjent(int id, String nazwisko, String imie, String pesel, LocalDate dataUrodzenia) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
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

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }


    @Override
    public String toString() {
        return "Pacjent{" +
                "id=" + id +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", iloscWizyt=" + listaWizyt.size() +
                '}';
    }
}
