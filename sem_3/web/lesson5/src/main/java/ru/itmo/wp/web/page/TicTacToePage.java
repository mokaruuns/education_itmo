package ru.itmo.wp.web.page;

import ru.itmo.wp.model.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
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
        String size = request.getParameter("size");
        if (isDigit(size) && Integer.parseInt(size)<=10) {
            state = new State(Integer.parseInt(request.getParameter("size")));
        } else {
            state = new State(3);
        }
        state.setPhase("RUNNING");
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
        makeMove(x, y, state);
        view.put("state", state);
    }

    private void makeMove(int x, int y, State state) {
        if (!state.getPhase().equals("RUNNING") || state.getCells()[x][y] != null) {
            return;
        }
        state.getCells()[x][y] = state.getCrossesMove() ? "X" : "O";
        state.subtractCountEmpty();
        if (countElements(x, y, 0, 1, state) + countElements(x, y, 0, -1, state) + 1 >= state.getSize() // горизонталь
                || countElements(x, y, 1, 0, state) + countElements(x, y, -1, 0, state) + 1 >= state.getSize() // вертикаль
                || countElements(x, y, 1, 1, state) + countElements(x, y, -1, -1, state) + 1 >= state.getSize() // побочная диагональ
                || countElements(x, y, 1, -1, state) + countElements(x, y, -1, 1, state) + 1 >= state.getSize() // главная диагональ)
        ) {
            state.setPhase("WON_" + (state.getCrossesMove() ? "X" : "O"));
            return;
        }
        if (state.getCountEmpty() <= 0) {
            state.setPhase("DRAW");
            return;
        }

        state.flipCrosses();
        state.setPhase("RUNNING");
    }

    private int countElements(int x, int y, int i, int j, State state) {
        x += i;
        y += j;
        int countElement = 0;
        while (x >= 0 && x < state.getSize() && y >= 0 && y < state.getSize() && state.getCells()[x][y] != null && state.getCells()[x][y].equals(state.getCrossesMove() ? "X" : "O")) {
            countElement++;
            x += i;
            y += j;
        }
        return countElement;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
