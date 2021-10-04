package ua.goit.controller;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serial;


@WebServlet(urlPatterns = "/")
public class Servlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7378720564698491220L;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }
}
