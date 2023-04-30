package br.com.bestmovies.principal;

import br.com.bestmovies.api.ImdbApiClient;
import br.com.bestmovies.modelo.Filme;
import br.com.bestmovies.modelo.FilmeRecord;
import br.com.bestmovies.modelo.HTMLGenerator;
import br.com.bestmovies.modelo.LeitorDeJson;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ImdbApiClient api = new ImdbApiClient();
        LeitorDeJson leitorDeJson = new LeitorDeJson();

        List<Filme> filmes = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            //Retorna o json da APIm=, separa os objetos javascript no array filmesJSON, converte esses objetos em filmeRecord.
            String json = api.buscaPaginaDeMelhoresFilmes(i);
            String[] filmesJSON = leitorDeJson.separaObjetos(json);
            List<FilmeRecord> filmeRecordList = leitorDeJson.converteJsonEmFilmeRecord(filmesJSON);

            //Converte os filmeRecord em Filme
            for (FilmeRecord filmeRecord :
                    filmeRecordList) {
                Filme filme = new Filme(filmeRecord);
                filmes.add(filme);
            }
        }


        //Cria um arquivo html que exibe a lista dos top 240 filmes.
        Writer write;
        try {
            write = new PrintWriter("filmes.html");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        HTMLGenerator htmlGenerator = new HTMLGenerator(write);
        htmlGenerator.generator(filmes);
        try {
            write.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
