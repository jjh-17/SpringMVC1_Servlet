package hello.servlet.membership.front_controller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;
import hello.servlet.membership.front_controller.MyView;
import hello.servlet.membership.front_controller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        //Model에 데이터 보관
        request.setAttribute("members", members);

        //V1과 달리 MyView 객체를 이용하여 페이지 이동 중복 코드 제거
        return new MyView("/WEB-INF/views/members.jsp");

    }
}
