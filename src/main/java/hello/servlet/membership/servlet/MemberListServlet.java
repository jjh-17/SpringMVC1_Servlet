package hello.servlet.membership.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/membership/servlet/members")
public class MemberListServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("   <meta charset=\"UTF-8\">\n");
        w.write("   <title>Title</title>\n");
        w.write("</head>\n");
        w.write("<body>\n");
        w.write("<a href=\"/index.html\">메인</a>\n");
        w.write("<table>\n");
        w.write("   <thead>\n");
        w.write("   <th>id</th>\n");
        w.write("   <th>username</th>\n");
        w.write("   <th>age</th>\n");
        w.write("   </thead>\n");
        w.write("   <tbody>\n");

        for (Member member : members) {
            w.write("   <tr>\n");
            w.write("       <td>" + member.getId() + "</td>\n");
            w.write("       <td>" + member.getUsername() + "</td>\n");
            w.write("       <td>" + member.getAge() + "</td>\n");
            w.write("   </tr>\n");
        }
        w.write("   </tbody>\n");
        w.write("</table>\n");
        w.write("</body>\n");
        w.write("</html>\n");

    }
}
