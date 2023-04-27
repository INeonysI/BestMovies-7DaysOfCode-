package br.com.bestmovies.principal;

import br.com.bestmovies.api.API;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        API api = new API();
        List<String> nomes = new ArrayList<>();
        List<String> links = new ArrayList<>();
        List<String> anosDeLancamento = new ArrayList<>();
        List<String> notas = new ArrayList<>();



        //Documentação:
        //https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
        Pattern regexParaNomes = Pattern.compile("\"title\":\"([^\"]+)\"");
        Pattern regexParaLinks = Pattern.compile("\"poster_path\":\"([^\"]+)\"");
        Pattern regexParaDatas = Pattern.compile("\"release_date\":\"(\\d{4})-\\d{2}-\\d{2}\"");

        Pattern regexParaNotas = Pattern.compile("\"vote_average\":\\s*([0-9]+(?:\\.[0-9]+)?)");


        for (int i = 1; i <= 12; i++) {
            String json = api.buscaPaginaDeMelhoresFilmes(i);

            //Documentação:
            //https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html
            Matcher matcherParaNomes = regexParaNomes.matcher(json);
            Matcher matcherParaLinks = regexParaLinks.matcher(json);
            Matcher matcherParaDatas = regexParaDatas.matcher(json);
            Matcher matcherParaNotas = regexParaNotas.matcher(json);

            while (matcherParaNomes.find() && matcherParaLinks.find() && matcherParaDatas.find() && matcherParaNotas.find()) {
                nomes.add(matcherParaNomes.group(1));
                links.add("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + matcherParaLinks.group(1));
                notas.add(matcherParaNotas.group(1));
                anosDeLancamento.add(matcherParaDatas.group(1));
            }
        }

        for (int i = 0; i < nomes.size(); i++) {
            System.out.printf("""
                    Filme %d ->
                    Nome: %s
                    Nota: %s
                    Link do poster: %s
                    Ano de lançamento: %s
                    %n""", i + 1, nomes.get(i), notas.get(i), links.get(i), anosDeLancamento.get(i));
        }


    }
}
