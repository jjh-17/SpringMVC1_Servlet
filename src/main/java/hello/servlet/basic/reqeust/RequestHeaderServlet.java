package hello.servlet.basic.reqeust;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printETC(req);
    }

    //Start Line 정보 출력
    private void printStartLine(HttpServletRequest req) {
        System.out.println("--- 스타트 라인 정보 출력 ---");

        System.out.println("request.getMethod() = " + req.getMethod());             //GET
        System.out.println("request.getProtocol() = " + req.getProtocol());         //HTTP/1.1
        System.out.println("request.getScheme() = " + req.getScheme());             //http
        System.out.println("request.getRequestURL() = " + req.getRequestURL());     //http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + req.getRequestURI());     //'/request-header'
        System.out.println("request.getQueryString() = " + req.getQueryString());   //?로 시작하는 쿼리문의 여부
        System.out.println("request.isSecure() = " + req.isSecure());               //https 사용 유무
        System.out.println();
    }

    //헤더 모든 정보 출력
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- 헤더 정보 조회 ---");

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));
        System.out.println();
    }

    //Header 편리하게 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- 헤더 편리하게 조회 ---");

        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale)); 
        System.out.println("request.getLocale() = " + request.getLocale());             //최고값을 가지는 언어 반환
        System.out.println();

        System.out.println("[Cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println("cookie.getName() = " + cookie.getName());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType()); //GET 이므로 body에 내용이 없음
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println();
    }

    //기타 정보 출력
    private void printETC(HttpServletRequest request) {
        System.out.println("--- 기타 정보 조회 ---");

        System.out.println("[Remote 정보]"); //들어온 요청에 대한 정보
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]"); //현재 서버에 대한 정보
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
    }
}
