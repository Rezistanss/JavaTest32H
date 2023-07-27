package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

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
                Lekarz lekarz = wyszukajObiektPoID(listaLekarzy, idLekarza, s -> s.getId() == idLekarza);
                Pacjent pacjent = wyszukajObiektPoID(listaPacjentow, idPacjenta, s -> s.getId() == idPacjenta);
                listaWizyt.add(new Wizyta(lekarz, pacjent, dataWizyty));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> int getIdFor(T object) {
        if (object instanceof Lekarz) {
            return ((Lekarz) object).getId();
        } else if (object instanceof Pacjent) {
            return ((Pacjent) object).getId();
        } else {
            throw new IllegalArgumentException("Obiekt nie jest typu Lekarz ani Pacjent");
        }
    }

    public <T> T wyszukajObiektPoID(List<T> lista, int id, Predicate<T> predicate) {
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("lista jest pusta");
        }
        for (T object : lista) {
            if (predicate.test(object) && getIdFor(object) == id) {
                return object;
            }
        }
        throw new NullPointerException("Nie ma osoby o podanym ID");
    }

    public Lekarz znajdzLekarzaZNajwiekszaIlosciaWizyt() {
        Map<Integer, Integer> mapaLiczbyWizytLekarzy = new HashMap<>();
        for (Wizyta wizyta : listaWizyt) {
            int idLekarza = wizyta.getLekarz().getId();
            mapaLiczbyWizytLekarzy.put(idLekarza, mapaLiczbyWizytLekarzy.getOrDefault(idLekarza, 0) + 1);
        }
        int maxWizyt = 0;
        int idLekarzaZNajwiekszaIlosciaWizyt = -1;
        for (Map.Entry<Integer, Integer> entry : mapaLiczbyWizytLekarzy.entrySet()) {
            if (entry.getValue() > maxWizyt) {
                maxWizyt = entry.getValue();
                idLekarzaZNajwiekszaIlosciaWizyt = entry.getKey();
            }
        }
        int finalIdLekarzaZNajwiekszaIlosciaWizyt = idLekarzaZNajwiekszaIlosciaWizyt;
        return wyszukajObiektPoID(listaLekarzy, idLekarzaZNajwiekszaIlosciaWizyt, s -> s.getId() == finalIdLekarzaZNajwiekszaIlosciaWizyt);
    }

    public Pacjent znajdzPacjentaZNajwiekszaIlosciaWizyt() {
        Map<Integer, Integer> mapaLiczbyWizytPacjenta = new HashMap<>();
        for (Wizyta wizyta : listaWizyt) {
            int idPacjenta = wizyta.getPacjent().getId();
            mapaLiczbyWizytPacjenta.put(idPacjenta, mapaLiczbyWizytPacjenta.getOrDefault(idPacjenta, 0) + 1);
        }
        int maxWizyt = 0;
        int idPacjentaZnajwiekszaIlosciaWizyt = -1;
        for (Map.Entry<Integer, Integer> entry : mapaLiczbyWizytPacjenta.entrySet()) {
            if (entry.getValue() > maxWizyt) {
                maxWizyt = entry.getValue();
                idPacjentaZnajwiekszaIlosciaWizyt = entry.getKey();
            }
        }
        int finalIdPacjentaZnajwiekszaIlosciaWizyt = idPacjentaZnajwiekszaIlosciaWizyt;
        return wyszukajObiektPoID(listaPacjentow, idPacjentaZnajwiekszaIlosciaWizyt, s -> s.getId() == finalIdPacjentaZnajwiekszaIlosciaWizyt);
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

    public List<Lekarz> top5NajstarszychLekarzy() {
        List<Lekarz> posortowaniLekarze = new ArrayList<>(listaLekarzy);
        if (posortowaniLekarze.size() < 5) {
            throw new ArrayStoreException("Podana lista jest za mała by wygenerować top 5 zawodników");
        }
        posortowaniLekarze.sort(Comparator.comparing(Lekarz::getDataUrodzenia));
        int liczbaLekarzy = posortowaniLekarze.size();
        int liczbaTopLekarzy = Math.min(5, liczbaLekarzy);
        List<Lekarz> topLekarze = new ArrayList<>();
        for (int i = 0; i < liczbaTopLekarzy; i++) {
            int indeks = liczbaLekarzy - 1 - i;
            topLekarze.add(posortowaniLekarze.get(indeks));
        }
        return topLekarze;
    }

    public List<Pacjent> pacjenciZMin5RoznymiLekarzami() {
        Map<Integer, Set<Integer>> pacjentLekarzeMap = new HashMap<>();
        for (Wizyta wizyta : listaWizyt) {
            int pacjentId = wizyta.getPacjent().getId();
            int lekarzId = wizyta.getLekarz().getId();
            pacjentLekarzeMap.computeIfAbsent(pacjentId, k -> new HashSet<>()).add(lekarzId);
        }
        List<Pacjent> pacjenciZMin5RoznymiLekarzami = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : pacjentLekarzeMap.entrySet()) {
            if (entry.getValue().size() >= 5) {
                int pacjentId = entry.getKey();
                pacjenciZMin5RoznymiLekarzami.add(wyszukajObiektPoID(listaPacjentow, pacjentId, s -> s.getId() == pacjentId));
            }
        }
        return pacjenciZMin5RoznymiLekarzami;
    }

    public List<Lekarz> lekarzeExclusive() {
        Map<Integer, Set<Integer>> lekarzPacjenciMap = new HashMap<>();
        for (Wizyta wizyta : listaWizyt) {
            int lekarzId = wizyta.getLekarz().getId();
            int pacjentId = wizyta.getPacjent().getId();
            lekarzPacjenciMap.computeIfAbsent(lekarzId, k -> new HashSet<>()).add(pacjentId);
        }
        List<Lekarz> lekarzeExclusive = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : lekarzPacjenciMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                int lekarzId = entry.getKey();
                lekarzeExclusive.add(wyszukajObiektPoID(listaLekarzy, lekarzId, s -> s.getId() == lekarzId));
            }
        }
        if (lekarzeExclusive.isEmpty()) {
            System.out.println("Nie ma lekarza, który miałby tylko jednego pacjenta");
            return Collections.emptyList();
        }
        return lekarzeExclusive;
    }
}
