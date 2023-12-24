package hello.servlet.membership.front_controller.v3.controller;


import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;
import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> map) {
        List<Member> members = memberRepository.findAll();

        //v2와 달리 request가 아닌 모델에 데이터 보관
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        //v2와 달리 뷰가 아닌 모델 반환
        return modelView;
    }
}
