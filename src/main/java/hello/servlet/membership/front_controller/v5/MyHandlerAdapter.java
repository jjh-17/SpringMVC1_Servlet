package hello.servlet.membership.front_controller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.ModelView;

import java.io.IOException;

//핸들러(컨트롤러) 호출 인터페이스
public interface MyHandlerAdapter {

    //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단
    boolean supports(Object handler);

    //어댑터가 실제 컨트롤러(handler) 호출, 결과 ModelView 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException;
}
