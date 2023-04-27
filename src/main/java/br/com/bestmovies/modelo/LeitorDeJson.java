package br.com.bestmovies.modelo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeitorDeJson {
    public String[] separaObjetos(String json) {
        String regex = "\\[(.*)\\]";
        Matcher matcher = Pattern.compile(regex).matcher(json);

        //Pega o conteúdo de results: []
        matcher.find();
        String jsonComColchetes = matcher.group(0);

        //Pega apenas os objetos do json
        String objetos = jsonComColchetes.substring(1, jsonComColchetes.length() - 1);

        //Separa os objetos
        String[] filmes = objetos.split("\\},\\{");

        //retira as chaves do primeiro e ultimo String
        filmes[0] = filmes[0].substring(1);
        filmes[filmes.length - 1] = filmes[filmes.length - 1].substring(0, filmes[filmes.length - 1].length() - 1);

        return filmes;
    }

    public List<FilmeRecord> converteJsonEmFilmeRecord(String[] filmes) {
        List<FilmeRecord> filmeRecordList = new ArrayList<>();
        Gson gson = new Gson();
        for (String filme :
                filmes) {
            FilmeRecord filmeRecord = gson.fromJson("{" + filme + "}", FilmeRecord.class);
            filmeRecordList.add(filmeRecord);
        }
        return filmeRecordList;
    }
}
