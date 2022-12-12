import java.util.List;

public class Tree {

    String node;

    List<Tree> children;

    public Tree(String node, List<Tree> children) {
        this.node = node;
        this.children = children;
    }

    public Tree(String node) {
        this.node = node;
    }

}
