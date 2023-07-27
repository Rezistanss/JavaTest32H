package zad1;

public class Main {
    public static void main(String[] args) {
        WczytywanieDanych wczytywanieDanych = new WczytywanieDanych();
        wczytywanieDanych.wczytajWszystkieDane();
        System.out.println("Lekarz z największą ilością wizyt to: " + wczytywanieDanych.znajdzLekarzaZNajwiekszaIlosciaWizyt());
        System.out.println("Pacjent z największą ilością wizyt to: " + wczytywanieDanych.znajdzPacjentaZNajwiekszaIlosciaWizyt());
        System.out.println(wczytywanieDanych.specjalizacjaZNajwiekszymPowodzeniem());
        System.out.println(wczytywanieDanych.rokZNajwiekszaIlosciaWizyt());
        System.out.println(wczytywanieDanych.top5NajstarszychLekarzy());
        System.out.println(wczytywanieDanych.lekarzeExclusive());
        System.out.println(wczytywanieDanych.pacjenciZMin5RoznymiLekarzami());
    }
}
