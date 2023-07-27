package zad1;

public class Main {
    public static void main(String[] args) {
        WczytywanieDanych wczytywanieDanych = new WczytywanieDanych();
        wczytywanieDanych.wczytajWszystkieDane();
        System.out.println(wczytywanieDanych.znajdzPacjentaZNajwiekszaIlosciaWizyt());
        System.out.println(wczytywanieDanych.znajdzLekarzaZNajwiekszaIlosciaWizyt());
        System.out.println(wczytywanieDanych.specjalizacjaZNajwiekszymPowodzeniem());
        System.out.println(wczytywanieDanych.rokZNajwiekszaIlosciaWizyt());
        System.out.println(wczytywanieDanych.topXNajstarszychLekarzy(1));
        System.out.println(wczytywanieDanych.pacjenciZMinXRoznymiLekarzami(3));
        System.out.println(wczytywanieDanych.lekarzeExclusive());


        System.out.println(wczytywanieDanych.znajdzOsobeZNajwiekszaIlosciaWizyt(wczytywanieDanych.getListaLekarzy(), Lekarz::getListaWizyt));
    }
}
