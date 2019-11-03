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

public class test_servlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 参数的获取
         */
        System.out.println("获取单值参数name:" + request.getParameter("name"));

        String[] hobits = request.getParameterValues("hobits");
        System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));

        System.out.println("通过 getParameterMap 遍历所有的参数： ");
        Map<String, String[]> parameters = request.getParameterMap();

        Set<String> paramNames = parameters.keySet();
        for (String param : paramNames) {
            String[] value = parameters.get(param);
            System.out.println(param + ":" + Arrays.asList(value));
        }
        /**
         * 测试各种路径
         * parameter可以再写一个filter进行测试
         * context  session的参数示例
         */
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write("<h1>getRequestURI"+request.getRequestURI()+"</h1><br>");
        response.getWriter().write("<h1>getRequestURL" + request.getRequestURL() + "</h1><br>");

        response.getWriter().write("<h1>getContextPath" + request.getContextPath() + "</h1><br>");
        response.getWriter().write("<h1>getServletPath" + request.getServletPath() + "</h1><br>");
        response.getWriter().write("<h1>getPathInfo" + request.getPathInfo() + "</h1><br>");

        response.getWriter().write("<h1>getServletContext.getContextPath" + request.getServletContext().getContextPath() + "</h1><br>");
        response.getWriter().write("<h1>getServletContext().getRealPath" + request.getServletContext().getRealPath("/") + "</h1><br>");
    }
}