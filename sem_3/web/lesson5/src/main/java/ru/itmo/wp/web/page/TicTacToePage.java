package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    public static class State {

        private int size;
        private String phase;
        private String[][] cells;
        private boolean crossesMove;

        public State(int size) {
            this.cells = new String[size][size];
            this.phase = "null";
            this.size = size;
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
        System.out.println(view.get("state"));
        if (request.getSession().getAttribute("state") == null) {
            newGame(request, view);
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
        System.out.println(x);
        state.cells[x][y] = state.crossesMove ? "X" : "O";
        state.crossesMove = !state.crossesMove;
        view.put("state", state);
//        checkEndGame(state.cells, state.size, x, y);
    }
}
