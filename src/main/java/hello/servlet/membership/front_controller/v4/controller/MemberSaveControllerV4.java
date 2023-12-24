package hello.servlet.membership.front_controller.v4.controller;

import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;
import hello.servlet.membership.front_controller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> map, Map<String, Object> model) {
        String username = map.get("username");
        int age = Integer.parseInt(map.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //V3와 달리, ModelView 생성 없이 파라미터로 넘어온 model에 데이터 저장
        model.put("member", member);

        //V3와 달리 ModelView를 사용하지 않고 View의 논리 이름을 반환
        return "save-result";
    }
}
