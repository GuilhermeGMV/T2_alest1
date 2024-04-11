public class Palavra {
    private String palavra;
    private String significado;
    private ArvorePalavras arvore;

    public Palavra(String palavra, String significado) {
        this.palavra = palavra;
        this.significado = significado;
        this.arvore = new ArvorePalavras();
        this.arvore.adicionarPalavra(palavra);
    }

    public String getPalavra() {
        return palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public ArvorePalavras getArvore() {
        return arvore;
    }

    @Override
    public String toString() {
        return palavra + ": " + significado + "\n";
    }
}




