package com.jmpaniego.msscbeerservice.web.model;

public enum BeerStyleEnum {
    LAGER,PILSNER, STOUT, GOSE, PORTER, ALE, WHEAT, IPA, PALE_ALE, SAISON, EMPTY;

    @Override
    public String toString() {
        if(name().equals("EMPTY"))
            return "";
        else return name();
    }
}
