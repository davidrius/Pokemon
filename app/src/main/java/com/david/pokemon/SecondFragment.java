package com.david.pokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.david.pokemon.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Obtenemos los datos del bundle del firstFragment

        Bundle datos = getArguments();

        //Comprobamos que en el bundle tengamos los datos del primer fragmento

        if(datos != null){

            //le pasamos al objeto Pokemon los datos del primer fragment

            Pokemon pokemon = (Pokemon) datos.getSerializable("item");

            //mostramos el nombre, altura y peso que hemos obtenido del bundle

            binding.txtNombreDetalle.setText(pokemon.getName());
            binding.txtAlturaDetalle.setText(String.valueOf(pokemon.getHeight()));
            binding.txtPesoDetalle.setText(String.valueOf(pokemon.getWeight()));

            //mostramos la imagen

            Glide.with(getContext()).load(pokemon.getImage()).into(binding.ivPokemon)

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}