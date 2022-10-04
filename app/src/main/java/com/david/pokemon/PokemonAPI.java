package com.david.pokemon;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonAPI {

    public ArrayList<Pokemon> getPokemons(){

        try{

            String url = "https://pokeapi.co/api/v2/pokemon/";
            String result = HttpUtils.get(url);
            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            for(int i = 0; i < results.length(); i++){

                JSONObject pokemonJson = results.getJSONObject(i);
                Pokemon pokemon = new Pokemon();
                pokemon.setName(pokemonJson.getString("name"));
                pokemon.setDetailsUrl(pokemonJson.getString("url"));

                String resultDetails = HttpUtils.get(pokemon.getDetailsUrl());
                JSONObject jsonDetails = new JSONObject(resultDetails);
                JSONObject sprites = new JSONObject();
                pokemon.setHeight(jsonDetails.getInt("height"));
                pokemons.add(pokemon);

            }

            Log.e("XXX POKEMONS", pokemons.toString());
            return pokemons;

        }catch(IOException e){

            e.printStackTrace();

        }catch(JSONException e){

            e.printStackTrace();

        }

        return null;

    }

}
