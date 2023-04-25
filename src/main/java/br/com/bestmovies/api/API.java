package br.com.bestmovies.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    public String buscaPaginaDeMelhoresFilmes(int pagina) {
        String json = null;
        String key = "03de48f66303824c443b36741744feac";
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + key + "&language=pt-BR&page=" + pagina;
        //Documentação:
        //https://docs.oracle.com/en/java/javase/20/docs/api/java.net.http/java/net/http/HttpRequest.html
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            //Documentação:
            //https://docs.oracle.com/en/java/javase/20/docs/api/java.net.http/java/net/http/HttpResponse.html
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        } catch(InterruptedException | IOException ex) {
            System.out.println(ex.getMessage());
        }

        return json;
    }
}
