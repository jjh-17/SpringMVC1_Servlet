package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.basic.data.HelloData;

import java.io.IOException;

//HTTP 응답으로 HTML 반환
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUserName("kim");
        helloData.setAge(20);

        //{"userName": "kim", "age":20}
        String result = objectMapper.writeValueAsString(helloData); //객체를 JSON 문자로 변경
        response.getWriter().write(result);

    }
}
