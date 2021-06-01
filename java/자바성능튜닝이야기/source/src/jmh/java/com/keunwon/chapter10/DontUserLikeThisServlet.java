package com.keunwon.chapter10;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DontUserLikeThisServlet extends HttpServlet {
    private String successFlag = "N";

    public DontUserLikeThisServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        successFlag = req.getParameter("successFlag");
    }
}
