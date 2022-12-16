package parser;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    String node;

    List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = List.of(children);
    }

    public Tree(String node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    public void addChild(Tree child) {
        if (child != null) {
            children.add(child);
        }
    }

    public String toString() {
        if (children == null || children.isEmpty()) {
            return node;
        } else {
            ArrayList<String> childrenStrings = new ArrayList<>();
            for (Tree child : children) {
                childrenStrings.add(child.toString());
            }
            return node + "(" + String.join(", ", childrenStrings) + ")";
        }
    }

    public List<Tree> getChildren() {
        return children;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String s) {
        this.node = s;
    }
}
