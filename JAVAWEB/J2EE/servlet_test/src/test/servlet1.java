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

public class servlet1 extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        /**
         * 只有name1有结果，显然，重定向之后参数丢失，但是如果保存在servletcontext或者session里面，数据始终保留
         * 但servletcontext的保存时效太长，一般还是用session保存
         */
        try{
            String name = (String)request.getAttribute("name");
            System.out.println(name);
        }catch (Exception e ){
            e.printStackTrace();
        }
        try{
            String name2 = (String)request.getSession().getAttribute("name2");
            System.out.println(name2);
        }catch (Exception e ){
            e.printStackTrace();
        }
        try{
            String name1 = (String)request.getServletContext().getAttribute("name");
            System.out.println(name1);
        }catch (Exception e ){
            e.printStackTrace();
        }
        request.getAttribute("name");
        response.getWriter().write("servlet1");
        response.getWriter().write("<h1>getContextPath" + request.getServletContext() + "</h1><br>");
        response.getWriter().write("<h1>getServletContext.getContextPath" + request.getServletContext().getServletContextName() + "</h1><br>");
    }
}

