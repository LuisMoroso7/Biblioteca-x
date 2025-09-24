import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private int ANO_PUBLICACAO_MINIMO = 1900;
    
    private List<Livro> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public Livro adicionar(Livro livro) throws Exception{
        if (livro == null)
            throw new Exception("Livro não pode ser nulo");

        livro.setTitulo(livro.getTitulo().trim());
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título não pode ser em branco");
        
        livro.setAutor(livro.getAutor().trim());
        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor não pode ser em branco");
        
        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO
                || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre 1900 e o ano atual");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        acervo.add(livro);
        return livro;
    }

    public List<Livro> pesquisar() {
        return acervo;
    }
    public List<Livro> pesquisar(String titulo) {
        return pesquisar(titulo,null);
    }

    public List<Livro> pesquisar(String titulo, String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                if (autor == null ||  
                    livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                livrosEncontrados.add(livro);
            }
                
        }
        return livrosEncontrados;
    }

        public void remover(int indice) {
            if (indice >= 0 && indice < acervo.size()) {
                Livro removido = acervo.remove(indice);
                    System.out.println("📕 Livro removido: " + removido.getTitulo());
        } else {
            System.out.println("⚠️ Índice inválido, vivente!");
        }
    }
}




