import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    
    public static final int ANO_PUBLICACAO_MINIMO = 1900;

    private List<Livro> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    
    public Livro adicionar(Livro livro) throws Exception {
        if (livro == null)
            throw new Exception("Livro não pode ser nulo");

        livro.setTitulo(livro.getTitulo().trim());
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título não pode ser em branco");

        livro.setAutor(livro.getAutor().trim());
        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação inválido");

        
        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(livro.getTitulo()) &&
                l.getAutor().equalsIgnoreCase(livro.getAutor()) &&
                l.getAnoPublicacao() == livro.getAnoPublicacao()) {
                throw new Exception("Livro já cadastrado no acervo");
            }
        }

        acervo.add(livro);
        return livro;
    }

    
    public List<Livro> listar() {
        return new ArrayList<>(acervo);
    }

    
    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro l : acervo) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                encontrados.add(l);
            }
        }
        return encontrados;
    }

   
    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro l : acervo) {
            if (l.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                encontrados.add(l);
            }
        }
        return encontrados;
    }

    
    public Livro remover(int indice) throws Exception {
        if (indice < 0 || indice >= acervo.size()) {
            throw new Exception("Índice inválido");
        }
        return acervo.remove(indice);
    }

    
    public void atualizar(int indice, Livro novoLivro) throws Exception {
        if (indice < 0 || indice >= acervo.size()) {
            throw new Exception("Índice inválido");
        }
        
        Livro antigo = acervo.remove(indice);
        try {
            this.adicionar(novoLivro);
        } catch (Exception e) {
            acervo.add(indice, antigo);
            throw e;
        }
    }

    
    public int contarLivros() {
        return acervo.size();
    }

    
    public List<Livro> pesquisarPorAno(int anoInicio, int anoFim) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro l : acervo) {
            if (l.getAnoPublicacao() >= anoInicio && l.getAnoPublicacao() <= anoFim) {
                encontrados.add(l);
            }
        }
        return encontrados;
    }

   
    public Livro getMaisAntigo() {
    if (acervo.isEmpty()) {
        return null;
    }
    Livro maisAntigo = acervo.get(0);
    for (Livro l : acervo) {
        if (l.getAnoPublicacao() < maisAntigo.getAnoPublicacao()) {
            maisAntigo = l;
        }
    }
    return maisAntigo;
}

    public Livro getMaisNovo() {
        if (acervo.isEmpty()) {
        return null;
    }
    Livro maisNovo = acervo.get(0);
    for (Livro l : acervo) {
        if (l.getAnoPublicacao() > maisNovo.getAnoPublicacao()) {
            maisNovo = l;
        }
    }
    return maisNovo;
}

}

