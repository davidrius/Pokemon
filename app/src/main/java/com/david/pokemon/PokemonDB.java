package com.david.pokemon;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonDB.class}, version = 1)

    public abstract class PokemonDB extends RoomDatabase {

        private static PokemonDB INSTANCE; //nos guardamos una instancia de la clase

        public static PokemonDB getDatabase(Context context) {

            //Si no tengo ninguna instancia de la base de datos

            if (INSTANCE == null) {

                //creo la instancia de la base de datos

                INSTANCE =
                        Room.databaseBuilder(
                                context.getApplicationContext(),
                                PokemonDB.class, "db"
                        ).build();
            }

            //Si existe la instancia a la base de datos la devolvemos
            return INSTANCE;
        }

        //NOs devuelve el DAO

        public abstract PokemonDao getPokemonDao();
    }

