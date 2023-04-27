package br.com.bestmovies.principal;

import br.com.bestmovies.api.API;
import br.com.bestmovies.modelo.Filme;
import br.com.bestmovies.modelo.FilmeRecord;
import br.com.bestmovies.modelo.LeitorDeJson;


import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        API api = new API();
        LeitorDeJson leitorDeJson = new LeitorDeJson();

        List<Filme> filmes = new ArrayList<>();

        //Retorna o json da APIm=, separa os objetos javascript no array filmesJSON, converte esses objetos em filmeRecord.
        String json = api.buscaPaginaDeMelhoresFilmes(1);
        String[] filmesJSON = leitorDeJson.separaObjetos(json);
        List<FilmeRecord> filmeRecordList = leitorDeJson.converteJsonEmFilmeRecord(filmesJSON);

        //Converte os filmeRecord em Filme
        for (FilmeRecord filmeRecord :
                filmeRecordList) {
            Filme filme = new Filme(filmeRecord);
            filmes.add(filme);
        }

        //Imprime todos os filmes
        for (Filme filme :
                filmes) {
            System.out.println(filme);
        }
    }
}
