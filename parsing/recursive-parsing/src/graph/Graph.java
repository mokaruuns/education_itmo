package graph;

import parser.Tree;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Graph {
    private final Map<Integer, Set<Integer>> graph;
    private final String regexStr;
    private final Map<Integer, String> labels;
    private Set<Integer> finish;
    private final Tree tree;
    private int counter;

    public Graph(Tree tree, String regexStr) {
        this.tree = tree;
        this.graph = new HashMap<>();
        this.labels = new HashMap<>();
        this.regexStr = regexStr;
        this.counter = 0;
    }

    public void toGraph(Tree tree) {
        String label = tree.getNode();
        Integer start = counter;
        labels.put(counter, label);
        ArrayList<Tree> children = new ArrayList<>(tree.getChildren());
        HashSet<Integer> childrenNumbers = new HashSet<>();

        for (int i = 0; i < children.size(); i++) {
            counter += i + 1;
            childrenNumbers.add(counter);
            toGraph(children.get(i));
        }
        graph.put(start, childrenNumbers);

    }

    public Tree getTree() {
        return tree;
    }

    public void build() {
        toGraph(tree);
    }

    public void print() {
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void toDot() {
        System.out.println("digraph G {");
        System.out.println("E [label=\"" + regexStr + "\"];");
        for (Map.Entry<Integer, String> entry : labels.entrySet()) {
            System.out.println(entry.getKey() + " [label=\"" + entry.getValue() + "\"];");
        }
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            for (Integer child : entry.getValue()) {
                System.out.println(entry.getKey() + " -> " + child + ";");
            }
        }
        System.out.println("}");
    }

    public void toDotFile(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println("digraph G {");
            out.println("E [label=\"regex= " + regexStr + "\"];");
            for (Map.Entry<Integer, String> entry : labels.entrySet()) {
                out.println(entry.getKey() + " [label=\"" + entry.getValue() + "\"];");
            }
            for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
                for (Integer child : entry.getValue()) {
                    out.println(entry.getKey() + " -> " + child + ";");
                }
            }
            out.println("}");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
