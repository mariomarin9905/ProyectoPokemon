/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMarinPokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Alien 12
 */
public class Other {
    @JsonProperty("official-artwork")
    public OfficialArtwork officialArtwork;

    public OfficialArtwork getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(OfficialArtwork officialArtwork) {
        this.officialArtwork = officialArtwork;
    }
}
