public class LivroFisico extends Livro {
    private int numeroExemplares;
    private String dimensoes;

    public int getNumeroExemplares() {
        return numeroExemplares;
    }
    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
    public String getDimensoes() {
        return dimensoes;
    }
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override  
    public String toString() {
        String dadosLivro = "Titulo=" + this.getTitulo() 
                + ", autor=" + this.getAutor()
                + ", anoPublicacao=" + this.getAnoPublicacao() 
                + ", numeroPaginas=" + this.getNumeroPaginas();

        return dadosLivro 
                + ", Exemplares: " + this.getNumeroExemplares()
                + ", Dimensões" + dimensoes; 
    }

    @Override
    public String getTipoLivro(){
        return "Livro Físico";
    }   

    
}
