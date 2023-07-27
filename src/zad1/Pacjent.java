package zad1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pacjent implements Identifiable {
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

    public int uIluLekarzyByl() {
        Set<Lekarz> set = new HashSet<>();
        for (Wizyta w : listaWizyt) {
            set.add(w.getLekarz());
        }
        return set.size();
    }

    public List<Wizyta> getListaWizyt() {
        return listaWizyt;
    }

    public void setListaWizyt(List<Wizyta> listaWizyt) {
        this.listaWizyt = listaWizyt;
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

    @Override
    public int getID() {
        return id;
    }
}
