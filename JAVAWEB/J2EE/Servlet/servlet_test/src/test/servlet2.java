package test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class servlet2 extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        /**
         * 测试servletcontext的作用范围
         * 这里重定向到servlet1，在servlet1里面获取参数，看结果
         * 多了一个context是因为在编辑tomcat的时候自己配的context_path;使用的时候把它设置成/就好
         */
        request.getSession().setAttribute("name2","12306");
        request.getServletContext().setAttribute("name","2.000000");
        request.setAttribute("name","2");
        response.getWriter().write("servlet2");
        response.getWriter().write("<h1>getContextPath" + request.getServletContext() + "</h1><br>");
        response.getWriter().write("<h1>getServletContext.getContextPath" + request.getServletContext().getServletContextName() + "</h1><br>");
        response.sendRedirect("/context/servlet1");
        request.getRequestDispatcher("/context/servlet1").forward(request,response);

    }
}
