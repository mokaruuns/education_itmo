
package ru.itmo.wp.servlet;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static ru.itmo.wp.util.ImageUtils.toPng;

public class CaptchaFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        boolean captchaPassed = (session.getAttribute("captchaPassed") != null && session.getAttribute("captchaPassed").toString().equals("true"));
        System.out.println(session.getAttribute("captchaPassed") + " - " + captchaPassed);
        System.out.println(request.getMethod() + " - " + requestURI);
        if (!requestURI.equals("/captcha")) {
            session.setAttribute("lastGET", requestURI);
        }
        String lastGET = session.getAttribute("lastGET").toString();
        if (captchaPassed) {
            System.out.println(lastGET);
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(lastGET);
            requestDispatcher.forward(request, response);
        } else {
            if (request.getMethod().equals("GET")) {
                showCaptcha(request, response);
            } else {
                session.setAttribute("captchaPassed", checkCaptcha(request));
                response.sendRedirect(lastGET);
            }
        }

    }

    void showCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int randomCaptcha = (int) (Math.random() * 900) + 100;
        HttpSession session = request.getSession();
        session.setAttribute("intCaptcha", randomCaptcha);
        byte[] bytes = toPng(String.valueOf(randomCaptcha));
        String encodedString = Base64.getEncoder().encodeToString(bytes);
        response.setContentType("text/html");
        response.getWriter().write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Капча</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"captcha\">\n" +
                "    <img src=\"data:image/png;base64," + encodedString + "\" alt=\"" + encodedString + "\" /><br/>\n" +
                "    Код с картинки: <label>\n" +
                "    <input type=\"text\" name=\"captcha\"/>\n" +
                "</label><br/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        response.getWriter().close();
    }

    boolean checkCaptcha(HttpServletRequest request) {
        return request.getParameter("captcha").equals(request.getSession().getAttribute("intCaptcha").toString());
    }

}