package br.com.bestmovies.principal;

import br.com.bestmovies.api.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        API api = new API();

        for (int i = 1; i <= 12; i++) {
            String json = api.buscaPaginaDeMelhoresFilmes(i);
            System.out.println(json);
        }
    }
}
