package hello.servlet.basic.reqeust;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import hello.servlet.basic.data.HelloData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
HTTP 요청 데이터 - API 메시지 바디 - JSON ==> 자주 사용하는 방식
content-type : application/json
message body : {"userName" : "abc", "age" : 10}

 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    //JSON 결과를 파싱해서 사용할 수있는 자바 객체로 변환하기 위한 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //메시지 body를 byte로 받는다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte 코드를 String으로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("messageBody = " + messageBody);
        System.out.println("helloData.userName = " + helloData.getUserName());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("requestBodyJsonServlet");
    }
}
