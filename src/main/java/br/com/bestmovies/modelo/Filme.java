package br.com.bestmovies.modelo;


public class Filme {
    private String nome;
    private String poster;
    private Float nota;
    private String anoDeLancamento;

    public Filme(FilmeRecord filmeRecord) {
        this.nome = filmeRecord.title();
        this.poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + filmeRecord.poster_path();
        this.nota = (float) filmeRecord.vote_average();
        this.anoDeLancamento = filmeRecord.release_date();
    }

    @Override
    public String toString() {
        return """
                    Filme:
                    Nome: %s
                    Nota: %s
                    Link do poster: %s
                    Ano de lançamento: %s
                """.formatted(nome, nota, poster, anoDeLancamento);
    }
}
