package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MessageServlet extends HttpServlet {
    public static final String MESSAGE_AUTH = "/message/auth";
    public static final String MESSAGE_FIND_ALL = "/message/findAll";
    public static final String MESSAGE_ADD = "/message/add";
    private final ArrayList<UserText> usersText = new ArrayList<>();

//    private enum Kek {
//        AUTH("auth"),
//        FIND_ALL("auth"),
//        ADD("auth");
//
//        private final String path;
//
//        Kek(String path) {
//            this.path = path;
//        }
//    }

//    private static Map<String, Kek> tmp = new HashMap<>();
//
//    static {
//        for (Kek x : Kek.values()) {
//            tmp.put(x.path, x);
//        }
//    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        synchronized (this) {
            HttpSession session = request.getSession();
            String postUri = request.getRequestURI();
            response.setContentType("application/json");
            System.out.println(postUri);
//            Kek kek = tmp.get(postUri);
//            switch (kek) {
//
//                case AUTH:
//                    break;
//                case FIND_ALL:
//                    break;
//                case ADD:
//                    break;
//            }
            switch (postUri) {
                case MESSAGE_AUTH:
                    String requestUser = request.getParameter("user");
                    if (requestUser != null) {
                        session.setAttribute("user", requestUser);
                    }
                    Object sessionUser = session.getAttribute("user");
                    String json = new Gson().toJson(sessionUser == null ? "" : sessionUser);
                    System.out.println(json);
                    response.getWriter().print(json);
                    response.getWriter().flush();
                    break;
                case MESSAGE_FIND_ALL:
                    json = new Gson().toJson(usersText);
                    response.getWriter().print(json);
                    response.getWriter().flush();
                    break;
                case MESSAGE_ADD:
                    session = request.getSession();
                    sessionUser = session.getAttribute("user");
                    String requestText = request.getParameter("text");
                    UserText userText = new UserText((sessionUser == null ? "" : (String) sessionUser), requestText);
                    usersText.add(userText);
                    json = new Gson().toJson(userText);
                    response.getWriter().print(json);
                    response.getWriter().flush();
                    break;
            }
        }
    }
}
