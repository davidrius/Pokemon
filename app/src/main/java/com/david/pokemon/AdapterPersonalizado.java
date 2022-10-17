package com.david.pokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterPersonalizado extends ArrayAdapter<Pokemon> {
    public AdapterPersonalizado(Context context, int resource, List<Pokemon> object) {
        super(context,resource,object);
    }

    @NonNull
    @Override
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
    }

}
