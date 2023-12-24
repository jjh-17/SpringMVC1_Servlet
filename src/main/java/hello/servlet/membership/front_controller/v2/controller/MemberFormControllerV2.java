package hello.servlet.membership.front_controller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.MyView;
import hello.servlet.membership.front_controller.v2.ControllerV2;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //V1과 달리 MyView 객체를 이용하여 페이지 이동 중복 코드 제거
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
