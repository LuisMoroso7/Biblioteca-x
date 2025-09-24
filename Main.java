import java.util.List;
import java.util.Scanner;

public class Main {
    //Dependências
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String menu = """
                === Sistema Biblioteca ===
                Escolha uma das opções abaixo:
                1 - Adicionar Livro
                2 - Listar Acervo
                3 - Pesquisar Livro
                4 - Remover Livro
                5 - Atualizar Livro
                6 - Contagem de Livros
                7 - Pesquisar por Intervalo de Anos
                8 - Livro Mais Antigo e Mais Novo
                0 - Sair
                """;
        int opcao;
        do {
            System.out.println(menu);
            opcao = Input.scanInt("Digite sua escolha: ", scan);
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 2:
                    listarAcervo();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 3:
                    pesquisarLivro();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 4:
                    removerLivro();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 5:
                    atualizarLivro();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 6:
                    System.out.println("Total de livros no acervo: " + biblioteca.contarLivros());
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 7:
                    pesquisarPorAno();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 8:
                    mostrarMaisAntigoENovo();
                    System.out.println("Pressione Enter para continuar");
                    scan.nextLine();
                    break;
                case 0:
                    System.out.println("Volte Sempre!!!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro(){
        System.out.println("Qual tipo de livro deseja cadastrar?");
        System.out.println("1 - Físico");
        System.out.println("2 - Digital");
        int tipo = Input.scanInt("Escolha: ", scan);

        String titulo = Input.scanString("Digite o Título: ", scan);
        String autor = Input.scanString("Digite o Autor: ", scan);
        int anoPublicacao = Input.scanInt("Digite o ano de publicação: ", scan);
        int numeroPaginas = Input.scanInt("Digite o número de páginas: ", scan);

        Livro novoLivro;
        if (tipo == 1) {
            novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas);
        } else {
            novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas);
        }

        try {
            biblioteca.adicionar(novoLivro);
            System.out.println("Livro adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAcervo() {
        List<Livro> acervo = biblioteca.listar();
        System.out.println("Livros cadastrados");
        for (int i = 0; i < acervo.size(); i++) {
            System.out.println(i + " - " + acervo.get(i));
        }
    }

    private static void pesquisarLivro() {
        String titulo = Input.scanString("Digite o título que procuras: ", scan);
        List<Livro> livros = biblioteca.pesquisarPorTitulo(titulo);
        for (Livro livro : livros) {
            System.out.println(livro);
        }

        String autor = Input.scanString("Deseja pesquisar também por autor? (digite ou deixe vazio): ", scan);
        if (!autor.isEmpty()) {
            List<Livro> livrosAutor = biblioteca.pesquisarPorAutor(autor);
            for (Livro livro : livrosAutor) {
                System.out.println(livro);
            }
        }
    }

    private static void removerLivro() {
        listarAcervo();
        int indice = Input.scanInt("Digite o índice do livro que deseja remover: ", scan);
        try {
            biblioteca.remover(indice);
            System.out.println("Livro removido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarLivro() {
        listarAcervo();
        int indice = Input.scanInt("Digite o índice do livro que deseja atualizar: ", scan);

        System.out.println("Informe os novos dados do livro:");
        String titulo = Input.scanString("Digite o Título: ", scan);
        String autor = Input.scanString("Digite o Autor: ", scan);
        int anoPublicacao = Input.scanInt("Digite o ano de publicação: ", scan);
        int numeroPaginas = Input.scanInt("Digite o número de páginas: ", scan);

        System.out.println("Qual o tipo do livro?");
        System.out.println("1 - Físico");
        System.out.println("2 - Digital");
        int tipo = Input.scanInt("Escolha: ", scan);

        Livro novoLivro;
        if (tipo == 1) {
            novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas);
        } else {
            novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas);
        }

        try {
            biblioteca.atualizar(indice, novoLivro);
            System.out.println("Livro atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void pesquisarPorAno() {
        int anoInicio = Input.scanInt("Digite o ano inicial: ", scan);
        int anoFim = Input.scanInt("Digite o ano final: ", scan);
        List<Livro> livros = biblioteca.pesquisarPorAno(anoInicio, anoFim);
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void mostrarMaisAntigoENovo() {
        Livro maisAntigo = biblioteca.getMaisAntigo();
        Livro maisNovo = biblioteca.getMaisNovo();

        System.out.println("Livro mais antigo: " + (maisAntigo != null ? maisAntigo : "Nenhum livro no acervo"));
        System.out.println("Livro mais novo: " + (maisNovo != null ? maisNovo : "Nenhum livro no acervo"));
    }
}