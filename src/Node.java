import java.util.ArrayList;
import java.util.List;

public class Node {
    char character;
    List<Node> children;
    boolean ultimo;

    public Node(char character) {
        this.character = character;
        this.children = new ArrayList<>();
        this.ultimo = false;
    }
}