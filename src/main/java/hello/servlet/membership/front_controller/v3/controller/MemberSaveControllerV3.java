package hello.servlet.membership.front_controller.v3.controller;


import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;
import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> map) {
        String username = map.get("username");
        int age = Integer.parseInt(map.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //v2와 달리 request가 아닌 model에 데이터 보관
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);

        //v2와 달리 뷰가 아닌 모델 반환
        return modelView;
    }
}
