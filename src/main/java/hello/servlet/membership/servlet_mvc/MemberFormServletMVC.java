package hello.servlet.membership.servlet_mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "memberFormServletJspMVC", urlPatterns = "/membership/servlet-mvc/members/new-form")
public class MemberFormServletMVC extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

//        viewPath로 이동 및 정보 전달(페이지 이동)
//        forward는 기존 request, response 정보를 유지한채 이동
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }
}
