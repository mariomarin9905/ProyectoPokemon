
package com.digis01.MMarinPokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpritesTypes {
    
    @JsonProperty("generation-ix")
    public GenerationIX generationIX;

    public GenerationIX getGenerationIX() {
        return generationIX;
    }

    public void setGenerationIX(GenerationIX generationIX) {
        this.generationIX = generationIX;
    }
    
}
