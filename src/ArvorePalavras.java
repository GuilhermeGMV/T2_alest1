import java.util.ArrayList;
import java.util.List;

public class ArvorePalavras {
    private Node root;

    public ArvorePalavras() {
        this.root = new Node(' ');
    }

    public void adicionarPalavra(String palavra) { // cria a arvore palavra por palavra
        Node currentNode = root;

        for (char ch : palavra.toCharArray()) { // percorre a palavra inteira char por char
            Node childNode = null;

            for (Node child : currentNode.children) { // percorre todos os filhos do nodo atual
                if (child.character == ch) {
                    childNode = child;
                    break;
                }
            }

            if (childNode == null) { // se o nodo não existir, ele é criado e colocado como filho do nodo atual
                childNode = new Node(ch);
                currentNode.children.add(childNode);
            }

            currentNode = childNode; // atualiza o nodo atual
        }

        currentNode.ultimo = true; // identifica o ultimo nodo da palavra
    }

    public List<String> buscarPalavras(String prefixo) { // busca as palavras na arvore que tenham o prefixo indicado
        List<String> palavras = new ArrayList<>();
        Node currentNode = root;

        for (char ch : prefixo.toCharArray()) { // percorre o prefixo char por char
            Node childNode = null;

            for (Node child : currentNode.children) {  // percorre todos os filhos do nodo atual
                if (child.character == ch) {
                    childNode = child;
                    break;
                }
            }
            if (childNode == null) { // não foi encontrado palavras com esse prefixo
                return palavras;
            }

            currentNode = childNode; // atualiza o nodo atual
        }
        buscarPalavrasRecursivo(currentNode, prefixo, palavras);
        return palavras;
    }

    private void buscarPalavrasRecursivo(Node node, String palavraAtual, List<String> palavras) { // busca o restante da palavra a partir da palavraAtual(que começa sendo o prefixo)
        if (node.ultimo) {
            palavras.add(palavraAtual);
        }

        for (Node child : node.children) { // percorre todos os filhos do nó atual
            buscarPalavrasRecursivo(child, palavraAtual + child.character, palavras);
        }
    }
}
