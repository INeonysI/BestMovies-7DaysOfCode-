package br.com.bestmovies.modelo;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
    Writer writer;
    public HTMLGenerator(Writer writer) {
        this.writer = writer;
    }

    public void generator(List<Filme> filmes) {
        StringBuilder sectionMovies = new StringBuilder();
        for (Filme filme : filmes) {
            sectionMovies.append("""
                    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                        <h4 class="card-header">%s</h4>
                        <div class="card-body">
                            <img class="card-img" src="%s" alt="%s">
                            <p class="card-text mt-2">Nota: %s - Ano: %s</p>
                        </div>
                    </div>
                    """.formatted(filme.getNome(), filme.getPoster(), "Poster do filme " + filme.getNome(), filme.getNota(), filme.getAnoDeLancamento()));
        }
        String body = """
                <body>
                    <section class="movie-list">
                        %s
                    </section>
                </body>
                """.formatted(sectionMovies.toString());
        String head = """
                <head>
                    <meta charset="utf-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                    <title>Top 250 movies</title>
                    
                    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
                    <style>
                    body {
                        background-color: #6D5D6E;
                    }
                    .movie-list {
                        margin: 1rem;
                        display: flex;
                        flex-wrap: wrap;
                        justify-content: space-between;
                    }
                    </style>
                </head>
                """;
        String html = """
                <html>
                    %s
                    %s
                </html>
                """.formatted(head, body);

        try {
            writer.write(html);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
