package hello.servlet.membership.front_controller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.MyView;

import java.io.IOException;

//컨트롤러 인터페이스
public interface ControllerV2 {
    //V1과 달리, void가 아닌 MyView를 반환
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
