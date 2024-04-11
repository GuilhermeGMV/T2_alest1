import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        LinkedList<Palavra> lista = new LinkedList<>();
        String aux[];
        Path path1 = Paths.get("dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    aux = line.split(";");
                    aux[0] = aux[0].substring(0);
                    Palavra p = new Palavra(aux[0], aux[1]);
                    lista.add(p);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        for (Palavra palavra : lista) {
            palavra.getArvore().adicionarPalavra(palavra.getPalavra());
        }

        System.out.println("Lista de palavras e seus significados: " + lista);


        Scanner scanner = new Scanner(System.in);
        // pega o prefixo
        System.out.print("Digite o prefixo que deseja procurar: ");
        String prefixo = scanner.nextLine();
        System.out.println();
        prefixo = prefixo.substring(0,1).toUpperCase() + prefixo.substring(1).toLowerCase();
        // busca e printa as palavras com o prefixo indicado
        boolean i = true;
        System.out.print("Palavras com o prefixo '" + prefixo + "': ");
        for (Palavra palavra : lista) {
            List<String> palavrasEncontradas = palavra.getArvore().buscarPalavras(prefixo);
            if (!palavrasEncontradas.isEmpty()) {
                System.out.print(palavrasEncontradas);
                i = false;
            }
        }

        if(i){
            System.out.print("Não foi encontrado nenhuma palavra com este prefixo");
        }
        // buscar o significado da palavra "escolhida"
        for (Palavra palavra : lista) {
            List<String> palavrasEncontradas = palavra.getArvore().buscarPalavras(prefixo);

            if (!palavrasEncontradas.isEmpty()) {
                String palavraEscolhida = palavrasEncontradas.get(0);
                System.out.println();
                System.out.print("Significado da palavra " + palavraEscolhida + ": ");
                for (Palavra p : lista) {
                    if (p.getPalavra().equals(palavraEscolhida)) {
                        System.out.println(p.getSignificado());
                        break;
                    }
                }
                break;
            }
        }
    }
}
