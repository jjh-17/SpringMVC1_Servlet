package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//name=서블릿 이름, urlPatterns=URL 매핑
@WebServlet(name="helloServlet", urlPatterns="/hello")
public class HelloServlet extends HttpServlet {

    /*
    HelloServlet이 호출되면(매핑된 URL로 이동하면) service 호출
    request : http 요청 메시지 파싱 결과를 저장. HTTP 요청이 끝날 때까지 유지되는 임시 저장소 기능
    response : 응답 메시지
     */
    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("httpServletRequest = " + httpServletRequest);
        System.out.println("httpServletResponse = " + httpServletResponse);


        //***http query문으로 '/hello?username=이름' 작성 ==> 이름 추출***
        String username = httpServletRequest.getParameter("username");
        System.out.println("username = " + username);

//        ====응답 결과 설정====
        //http 메시지 Content Type(헤더 정보)에 저장
        httpServletResponse.setContentType("text/plain");
        httpServletResponse.setCharacterEncoding("utf-8");

        //http 메시지 Body에 저장
        httpServletResponse.getWriter().write("hello " + username); //화면에 문장 출력
    }
}
