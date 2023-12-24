package hello.servlet.basic.reqeust;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //메시지 body를 byte로 받는다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte 코드를 String으로 변환

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("requestBodyStringServlet");
    }
}
