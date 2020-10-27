package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    public static class State {

        private int size;
        private int countEmpty;
        private String phase;
        private String[][] cells;
        private boolean crossesMove;

        public State(int size) {
            this.cells = new String[size][size];
            this.phase = "null";
            this.size = size;
            this.countEmpty = size * size;
            this.crossesMove = true;
//            for (int i = 0; i < size; i++) {
//                for (int j = 0; j < size; j++) {
//                    this.cells[i][j] = "";
//                }
//            }
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

        public boolean isCrossesMove() {
            return crossesMove;
        }
    }

    // TODO: Implement it.
    State state;

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("state") == null) {
            newGame(request, view);
        } else {
            view.put("state", request.getSession().getAttribute("state"));
        }
    }


    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        System.out.println("newGame");
        state = new State(3);
        state.phase = "RUNNING";
        request.getSession().setAttribute("state", state);
        view.put("state", state);

    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        state = (State) request.getSession().getAttribute("state");
        Enumeration<String> names = request.getParameterNames();
        String req = "";
        for (String name : Collections.list(names)) {
            req = name;
        }
        int x = req.charAt(req.length() - 2) - '0';
        int y = req.charAt(req.length() - 1) - '0';
        state.phase = makeMove(x, y, state);
        view.put("state", state);
    }

    private String makeMove(int x, int y, State state) {
        if (!state.phase.equals("RUNNING") || state.cells[x][y]!= null){
            return state.phase;
        }
        state.cells[x][y] = state.crossesMove ? "X" : "O";
        state.countEmpty--;
        if (countElements(x, y, 0, 1, state) + countElements(x, y, 0, -1, state) + 1 >= state.size // горизонталь
                || countElements(x, y, 1, 0, state) + countElements(x, y, -1, 0, state) + 1 >= state.size // вертикаль
                || countElements(x, y, 1, 1, state) + countElements(x, y, -1, -1, state) + 1 >= state.size // побочная диагональ
                || countElements(x, y, 1, -1, state) + countElements(x, y, -1, 1, state) + 1 >= state.size // главная диагональ)
        ) {
            return "WON_"+ (state.crossesMove?"X":"O");
        }
        if (state.countEmpty <= 0) {
            return "DRAW";
        }

        state.crossesMove = !state.crossesMove;
        return "RUNNING";
    }
    private int countElements(int x, int y, int i, int j, State state) {
        x += i;
        y += j;
        int countElement = 0;
        while (x >= 0 && x < state.size && y >= 0 && y < state.size && state.cells[x][y]!= null && state.cells[x][y].equals(state.crossesMove? "X" : "O")) {
            countElement++;
            x += i;
            y += j;
        }
        return countElement;
    }
}
