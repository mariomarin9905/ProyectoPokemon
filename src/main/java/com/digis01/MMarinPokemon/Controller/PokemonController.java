package com.digis01.MMarinPokemon.Controller;
import com.digis01.MMarinPokemon.model.GenerationIX;
import com.digis01.MMarinPokemon.model.Icon;
import com.digis01.MMarinPokemon.model.Pokemon;
import com.digis01.MMarinPokemon.model.ResultList;
import com.digis01.MMarinPokemon.model.Species;
import com.digis01.MMarinPokemon.model.SpritesTypes;
import com.digis01.MMarinPokemon.model.Type;
import com.digis01.MMarinPokemon.model.TypeList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/PokemonWeb")
public class PokemonController {

    private RestTemplate restTemplate;
    private String BASEURL = "https://pokeapi.co/api/v2/pokemon";

    @GetMapping
    public String Home(Model model, @RequestParam(required = false) Integer page) {
        try {
            String indice = "offset=";
            if (page != null) {
                if (page > 0 && page < 110) {
                    indice = indice + this.GetOffset(page);
                } else if (page > 110) {
                    indice = indice + this.GetOffset(108);
                    page = 108;
                } else {
                    indice = indice + 0;
                }
            } else {
                indice = indice + 0;
                page = 0;
            }

            this.restTemplate = new RestTemplate();
            ResultList resultList = this.restTemplate.getForObject(this.BASEURL + "?limit=12&" + indice, ResultList.class);
            List<Pokemon> pokemons = resultList.results.stream().map(pokemon -> {
                return this.GetPokemonCard(pokemon.getUrl());
            }).collect(Collectors.toList());
            boolean next = (resultList.next == null) ? false : true;
            boolean previous = (resultList.previous == null) ? false : true;
            model.addAttribute("pokemons", pokemons);
            model.addAttribute("next", next);
            model.addAttribute("previous", previous);
            model.addAttribute("page", page);
            return "home";
        } catch (Exception e) {
            return "";
        }
    }

    public String GetOffset(int page) {
        int[] indices = new int[109];
        int iterador = 0;
        for (int i = 0; i < page; i++) {
            iterador += 12;
            indices[i] = iterador;
        }
        return String.valueOf(indices[page - 1]);
    }

    private Pokemon GetPokemon(String url) {
        this.restTemplate = new RestTemplate();
        return this.restTemplate.getForObject(url, Pokemon.class);
    }

    private Pokemon GetPokemonCard(String url) {
        Pokemon pokemon = this.GetPokemon(url);
        pokemon.types = pokemon.types.stream().map(typeList -> {
            typeList.setType(this.GetType(typeList.getType().getUrl()));
            return typeList;
        }).collect(Collectors.toList());
        pokemon.species = this.GetSpecies(pokemon.species.getUrl());
        return pokemon;
    }

    private Type GetType(String url) {
        return this.restTemplate.getForObject(url, Type.class);

    }

    private Species GetSpecies(String url) {
        return this.restTemplate.getForObject(url, Species.class);
    }
    
    @GetMapping("/Pokemon/{name}")
    public String Pokemon(@PathVariable String name, Model model){
        try {
            this.restTemplate = new RestTemplate();
            Pokemon pokemon = this.GetPokemonCard(this.BASEURL+"/"+name);
            model.addAttribute("pokemon", pokemon);
            return "Pokemon";
        } catch (Exception e) {
            return "";
        }
    }
}
