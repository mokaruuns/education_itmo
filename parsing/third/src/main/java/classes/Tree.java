package classes;

import java.util.ArrayList;
import java.util.List;


public class Tree {

    private final Nodes node;
    private final String content;

    List<Tree> children;


    public Tree(Nodes node, String content, List<Tree> children) {
        this.node = node;
        this.content = content;
        this.children = children;
    }

    public Tree(Nodes node, String content) {
        this.node = node;
        this.content = content;
        this.children = new ArrayList<>();
    }

    public Tree(Nodes node, List<Tree> children) {
        this.node = node;
        this.content = "";
        this.children = children;
    }


    public Tree(Nodes node) {
        this.node = node;
        this.content = "";
        this.children = new ArrayList<>();
    }

    public void addChild(Tree child) {
        if (child != null) {
            children.add(child);
        }
    }

    public String toString() {
        if (children == null || children.isEmpty()) {
            return node.toString();
        } else {
            ArrayList<String> childrenStrings = new ArrayList<>();
            for (Tree child : children) {
                childrenStrings.add(child.toString());
            }
            return node.toString() + "(" + String.join(", ", childrenStrings) + ")";
        }
    }

    public List<Tree> getChildren() {
        return children;
    }

    public Nodes getNode() {
        return node;
    }

    public String getContent() {
        return content;
    }

    public String toMathML() {
        switch (node) {
            case MI -> {
                return "<mi>" + content + "</mi>";
            }
            case MO -> {
                return "<mo>" + content + "</mo>";
            }
            case MN -> {
                return "<mn>" + content + "</mn>";
            }
            case MROW -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<mrow>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mrow>");
                return sb.toString();
            }
            case MATH -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<math>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</math>");
                return sb.toString();
            }
            case MSUB -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<msub>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</msub>");
                return sb.toString();
            }
            case MSUP -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<msup>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</msup>");
                return sb.toString();
            }
            case MFRAC -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<mfrac>");
                for (Tree child : children) {
                    sb.append("{");
                    sb.append(child.toMathML());
                    sb.append("}");
                }
                sb.append("</mfrac>");
                return sb.toString();
            }
            case MROOT -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<mroot>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mroot>");
                return sb.toString();
            }
            case MSQRT -> {
                StringBuilder sb = new StringBuilder();
                sb.append("<msqrt>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</msqrt>");
                return sb.toString();
            }
            default -> {
                return "";
            }
        }
    }
}
