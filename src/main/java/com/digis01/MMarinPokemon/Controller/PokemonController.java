package com.digis01.MMarinPokemon.Controller;

import com.digis01.MMarinPokemon.model.Pokemon;
import com.digis01.MMarinPokemon.model.ResultList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/PokemonWeb")
public class PokemonController {

    private RestTemplate restTemplate;
    private String BASEURL = "https://pokeapi.co/api/v2/pokemon";

    @GetMapping
    public String Home(Model model) {
        try {
            this.restTemplate = new RestTemplate();
            ResponseEntity<ResultList> responseResult = this.restTemplate.exchange(this.BASEURL + "?limit=10&offset=0",
                     HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<ResultList>() {
            });

            ResultList resultList = responseResult.getBody();
            List<Pokemon> pokemons = resultList.results;
            for (Pokemon pokemon : pokemons) {
                ResponseEntity<Pokemon> responsePokemon= this.restTemplate.exchange(this.BASEURL+"/"+pokemon.getName(),
                         HttpMethod.GET,
                        HttpEntity.EMPTY,
                        new ParameterizedTypeReference<Pokemon>() {
                });
                pokemon = responsePokemon.getBody();
                System.out.println();
            }
            model.addAttribute("pokemons", pokemons);
            return "home";
        } catch (Exception e) {
            return "";
        }

    }

}
