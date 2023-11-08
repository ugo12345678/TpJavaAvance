package sdv.java.m1.tp.service;




import sdv.java.m1.tp.bean.CritereFiltrage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtrage {

    public Stream<String> filtrer(List<String> lignes, CritereFiltrage critere) {
        return lignes.stream()
                .filter(ligne -> critere.getCriticites().stream()
                        .anyMatch(criticite -> ligne.startsWith(criticite.toString())))
                .filter(ligne -> ligne.toLowerCase().contains(critere.getSousChaine().toLowerCase()));
    }
}

