/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMarinPokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GenerationIX {
    
    @JsonProperty("scarlet-violet")
    public Icon scarletViolet;

    public Icon getScarletViolet() {
        return scarletViolet;
    }

    public void setScarletViolet(Icon scarletViolet) {
        this.scarletViolet = scarletViolet;
    }
    
   
    
}
