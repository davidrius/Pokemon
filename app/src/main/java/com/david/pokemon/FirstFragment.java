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
    //private ArrayAdapter adapter;
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

        ArrayList<String> items = new ArrayList<>();
        /*adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.lv_pokemon_row,
                R.id.txtTitle,
                items
        );*/

         adapter = new AdapterPersonalizado(
                getContext(),
                R.layout.lv_pokemon_row,
                R.id.txtTitle,
                items
        );

        binding.lvPokemon.setAdapter(adapter);
        refresh();

    }

    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        setHasOptionsMenu(true);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void refresh(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        String Tipos = preferences.getString("Tipos","Vacío");

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

        });

        Toast toast = Toast.makeText(getContext(),"Refrescando...", Toast.LENGTH_LONG);
        toast.show();

    }


    /*public class PokemonAdapter extends ArrayAdapter<Pokemon>{
        public PokemonAdapter(Context context, int resource, List<Pokemon> objects) {
            super(context,resource,objects);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Mirem a veure si la View s'està reutilitzant, si no es així "inflem" la View
        Pokemon pokemons = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvTitle = convertView.findViewById(R.id.txtTitle);
        ImageView ivImagen = convertView.findViewById(R.id.imgPokemon);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvTitle.setText(pokemons.getName());
        Glide.with(getContext()).load(pokemons.getImage()).into(ivImagen);

        // Retornem la View replena per a mostrar-la
        return convertView;
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}