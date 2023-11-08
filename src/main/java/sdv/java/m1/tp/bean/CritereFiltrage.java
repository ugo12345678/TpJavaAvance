package sdv.java.m1.tp.bean;

import sdv.java.m1.tp.enums.CriticiteAction;

import java.util.List;

public class CritereFiltrage {
    private List<CriticiteAction> criticites;
    private String sousChaine;

    // Getters and setters
    public List<CriticiteAction> getCriticites() {
        return criticites;
    }

    public void setCriticites(List<CriticiteAction> criticites) {
        this.criticites = criticites;
    }

    public String getSousChaine() {
        return sousChaine;
    }

    public void setSousChaine(String sousChaine) {
        this.sousChaine = sousChaine;
    }
}

