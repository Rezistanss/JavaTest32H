package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WczytywanieDanych {
    private final List<Lekarz> listaLekarzy = new ArrayList<>();
    private final List<Pacjent> listaPacjentow = new ArrayList<>();
    private final List<Wizyta> listaWizyt = new ArrayList<>();

    public void wczytajWszystkieDane() {
        wczytajDaneLekarzy();
        System.out.println("Wczytano " + listaLekarzy.size() + " lekarzy");
        wczytajDanePacjentow();
        System.out.println("Wczytano " + listaPacjentow.size() + " pacjentow");
        wczytajDaneWizyt();
        System.out.println("Wczytano " + listaWizyt.size() + " wizyt");
    }

    public void wczytajDaneLekarzy() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Zad1/plikiTxt/lekarze.txt"))) {
            String line;
            br.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                int id = Integer.parseInt(data[0]);
                String nazwisko = data[1];
                String imie = data[2];
                String specjalnosc = data[3];
                LocalDate dataUrodzenia = LocalDate.parse(data[4], formatter);
                String nip = data[5];
                String pesel = data[6];
                listaLekarzy.add(new Lekarz(id, nazwisko, imie, specjalnosc, dataUrodzenia, nip, pesel));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void wczytajDanePacjentow() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Zad1/plikiTxt/pacjenci.txt"))) {
            String line;
            br.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                int id = Integer.parseInt(data[0]);
                String nazwisko = data[1];
                String imie = data[2];
                String pesel = data[3];
                LocalDate dataUrodzenia = LocalDate.parse(data[4], formatter);
                listaPacjentow.add(new Pacjent(id, nazwisko, imie, pesel, dataUrodzenia));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void wczytajDaneWizyt() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Zad1/plikiTxt/wizyty.txt"))) {
            String line;
            br.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                int idLekarza = Integer.parseInt(data[0]);
                int idPacjenta = Integer.parseInt(data[1]);
                LocalDate dataWizyty = LocalDate.parse(data[2], formatter);
                Lekarz lekarz = wyszukajObiektPoID(listaLekarzy, idLekarza);
                Pacjent pacjent = wyszukajObiektPoID(listaPacjentow, idPacjenta);
                listaWizyt.add(new Wizyta(lekarz, pacjent, dataWizyty));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Identifiable> T wyszukajObiektPoID(List<T> lista, int id) {
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Lista osób jest pusta!");
        }
        Map<Integer, T> mapaObiektow = lista.stream().collect(Collectors.toMap(Identifiable::getID, Function.identity()));
        T znalezionyObiekt = mapaObiektow.get(id);
        if (znalezionyObiekt != null) {
            return znalezionyObiekt;
        }
        throw new IllegalArgumentException("Nie ma osoby o podanym ID");
    }

    public <T extends Identifiable> T znajdzOsobeZNajwiekszaIlosciaWizyt(List<T> list, Function<T, List<Wizyta>> function) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Lista osób jest pusta!");
        }
        T max = list.get(0);
        for (T t : list) {
            if (function.apply(t).size() > function.apply(max).size()) {
                max = t;
            }
        }
        return max;
    }

    public Pacjent znajdzPacjentaZNajwiekszaIlosciaWizyt() {
        return znajdzOsobeZNajwiekszaIlosciaWizyt(listaPacjentow, Pacjent::getListaWizyt);
    }

    public Lekarz znajdzLekarzaZNajwiekszaIlosciaWizyt() {
        return znajdzOsobeZNajwiekszaIlosciaWizyt(listaLekarzy, Lekarz::getListaWizyt);
    }

    public String specjalizacjaZNajwiekszymPowodzeniem() {
        Map<String, Integer> specjalizacjaCountMap = new HashMap<>();
        for (Lekarz lekarz : listaLekarzy) {
            String specjalizacja = lekarz.getSpecjalizacja();
            specjalizacjaCountMap.put(specjalizacja, specjalizacjaCountMap.getOrDefault(specjalizacja, 0) + 1);
        }
        return Collections.max(specjalizacjaCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public int rokZNajwiekszaIlosciaWizyt() {
        Map<Integer, Integer> rokCountMap = new HashMap<>();
        for (Wizyta wizyta : listaWizyt) {
            int rok = wizyta.getDataWizyty().getYear();
            rokCountMap.put(rok, rokCountMap.getOrDefault(rok, 0) + 1);
        }
        int maxIloscWizyt = 0;
        int rokZNajwiekszaIlosciaWizyt = 0;
        for (Map.Entry<Integer, Integer> entry : rokCountMap.entrySet()) {
            if (entry.getValue() > maxIloscWizyt) {
                maxIloscWizyt = entry.getValue();
                rokZNajwiekszaIlosciaWizyt = entry.getKey();
            }
        }
        return rokZNajwiekszaIlosciaWizyt;
    }

    public List<Lekarz> topXNajstarszychLekarzy(int x) {
        List<Lekarz> posortowaniLekarze = new ArrayList<>(listaLekarzy);
        if (posortowaniLekarze.size() < x) {
            throw new IllegalArgumentException("Podana lista jest za mała by wygenerować top 5 zawodników");
        }
        posortowaniLekarze.sort(Comparator.comparing(Lekarz::getDataUrodzenia));
        return posortowaniLekarze.subList(0, x);
    }

    public List<Pacjent> pacjenciZMinXRoznymiLekarzami(int x) {
        List<Pacjent> result = new ArrayList<>();
        for (Pacjent p : listaPacjentow) {
            if (p.uIluLekarzyByl() >= x) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Lekarz> lekarzeExclusive() {
        List<Lekarz> result = new ArrayList<>();
        for (Lekarz l : listaLekarzy) {
            if (l.iluMialUnikatowychPacjentow() == 1) {
                result.add(l);
            }
        }
        return result;
    }

    public List<Lekarz> getListaLekarzy() {
        return listaLekarzy;
    }
}
