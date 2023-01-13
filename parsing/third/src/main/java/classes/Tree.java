package classes;

import java.util.ArrayList;
import java.util.List;


public class Tree {

    private final Nodes node;
    private String content;

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

    public void setContent(String content) {
        this.content = content;
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
        StringBuilder sb = new StringBuilder();

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
                sb.append("<mrow>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mrow>");
                sb.append("\n");
            }
            case MATH -> {
                sb.append("<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\">");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</math>");
                sb.append("\n");
            }
            case MFRAC -> {
                sb.append("<mfrac>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mfrac>");
            }
            case MSQRT -> {
                sb.append("<msqrt>");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</msqrt>");
            }
            case MBRACE -> {
                sb.append("<mfenced open=\"[\" close=\"]\">");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mfenced>");
            }
            case MSQUARE -> {
                sb.append("<mfenced open=\"{\" close=\"}\">");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mfenced>");
            }
            case MPAREN -> {
                sb.append("<mfenced open=\"(\" close=\")\">");
                for (Tree child : children) {
                    sb.append(child.toMathML());
                }
                sb.append("</mfenced>");
            }
            default -> {
                return "";
            }
        }
        return sb.toString();

    }
}
