package com.david.pokemon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.david.pokemon.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private AdapterPersonalizado adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Pokemon> items = new ArrayList<>();

         adapter = new AdapterPersonalizado(
                getContext(),
                R.layout.lv_pokemon_row,
                items
        );

        binding.lvPokemon.setAdapter(adapter);

        binding.lvPokemon.setOnItemClickListener((adapterView, view1, i, l) -> {

            //devuelveme el item en la posicion
            Pokemon item = (Pokemon) adapterView.getItemAtPosition(i);

            //como un has map le decimos todo lo que va a haber
            //los datos los encapsulamos para llevarlos al second_fragment

            Bundle datos = new Bundle();
            datos.putSerializable("item",item);

            //irme a la otra pantalla fragment_second
            //nav_graph --> nos permite pasar de un frtagmento a otro es decir navegar
            //llevar los datos del bundle al segundo fragmento

            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment, datos);


        });

        PokemonsViewModel viewModel = new ViewModelProvider(getActivity()).get(PokemonsViewModel.class);
        //viewModel = new ViewModelProvider(getActivity()).get(PokemonsViewModel.class);
        viewModel.getPokemons().observe(getActivity(), pokemons ->{     //observe para ver si hay cambios

            adapter.clear();
            adapter.addAll(pokemons);

        });

        Log.e("DEBUG", String.valueOf(viewModel));

        viewModel.refresh();

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        setHasOptionsMenu(true);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {

            //viewModel.refresh();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /*public void refresh(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        String Tipos = preferences.getString("Tipos","Vac??o");

        if(!Tipos.equals("")){



        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() ->{

            PokemonAPI api = new PokemonAPI();
            ArrayList<Pokemon> pokemons = api.getPokemons();

            handler.post(() -> {

               adapter.clear();
               adapter.addAll(pokemons);

            });

            Log.e("DEBUG", String.valueOf(pokemons));

        });*/

        //Toast toast = Toast.makeText(getContext(),"Refrescando...", Toast.LENGTH_LONG);
        //toast.show();

    //}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}