package ru.itmo.wp.model;

public class State {

    private final int size;
    private int countEmpty;
    private String phase;
    private final String[][] cells;

    private boolean crossesMove;

    public State(int size) {
        this.cells = new String[size][size];
        this.phase = "null";
        this.size = size;
        this.countEmpty = size * size;
        this.crossesMove = true;
    }

    public int getSize() {
        return size;
    }

    public String getPhase() {
        return phase;
    }

    public String[][] getCells() {
        return cells;
    }

    public boolean getCrossesMove() {
        return crossesMove;
    }

    public int getCountEmpty() {
        return countEmpty;
    }

    public void setCrossesMove(boolean crossesMove) {
        this.crossesMove = crossesMove;
    }
    public void setPhase(String phase) {
        this.phase = phase;
    }
    public void subtractCountEmpty() {
        this.countEmpty--;
    }

    public void flipCrosses(){
        this.crossesMove = !this.crossesMove;
    }

}