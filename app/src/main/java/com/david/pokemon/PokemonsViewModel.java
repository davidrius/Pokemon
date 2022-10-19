package com.david.pokemon;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonsViewModel extends AndroidViewModel {

    private final Application app;
    private final Object pokemonDao;
    private final PokemonDB db;

    //Live Data para sincronizar el viewModel con el first Fragment

    private MutableLiveData<List<Pokemon>> pokemons;

    public PokemonsViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
        this.db = PokemonDB.getDatabase(this.app);
        this.pokemonDao = this.db.getPokemonDao();
    }

    //mutable live data es porque lo modificaba yo
    //Live data es porque lo que voy a poner no se va a modificar

    public LiveData<List<Pokemon>> getPokemons() {

        return pokemonDao.getPokemon();

        /*if (pokemons == null) {

            pokemons = new MutableLiveData<List<Pokemon>>();

        }

        return pokemons;*/

    }

    public void refresh() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());

        String Tipos = preferences.getString("Tipos","Vacío");

        if(!Tipos.equals("")){



        }

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {

            PokemonAPI api = new PokemonAPI();
            ArrayList<Pokemon> pokemons = api.getPokemons();

            this.pokemonDao.deletePokemon();
            this.pokemonDao.addPokemon();

        });

    }
}
