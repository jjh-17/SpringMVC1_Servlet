package hello.servlet.basic.reqeust;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
1. 파라미터 전송 기능
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getParameter : GET URL 쿼리 파라미터, POST HTML Form 형식 모두 지원
        printParamAll(request);
        printParamOne(request);
        printParamDupl(request);
    }

//    http://localhost:8080/request-param?username=abc&age=33
    private void printParamAll(HttpServletRequest request) {
        System.out.println("=== 전체 파라미터 조회 ===");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println();
    }

//    http://localhost:8080/request-param?username=abc&age=33
    private void printParamOne(HttpServletRequest request) {
        System.out.println("=== 단일 파라미터 조회 ===");

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();
    }

//    http://localhost:8080/request-param?username=abc&username=abc2&age=33
    private void printParamDupl(HttpServletRequest request) {
        System.out.println("=== 동일한 이름의 복수 파라미터 조회 ===");

        String[] usernames = request.getParameterValues("username");
        String[] ages = request.getParameterValues("age");

        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        for(String age : ages){
            System.out.println("age = " + age);
        }
        System.out.println();
    }
}
