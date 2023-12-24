package hello.servlet.membership.servlet_mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "memberListServletJspMVC", urlPatterns = "/membership/servlet-mvc/members")
public class MemberListServletMVC extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        //Model에 데이터 보관
        req.setAttribute("members", members);

        //viewPath로 이동 및 정보 전달(페이지 이동)
        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }
}
