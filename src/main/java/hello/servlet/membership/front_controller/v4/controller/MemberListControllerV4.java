package hello.servlet.membership.front_controller.v4.controller;

import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;
import hello.servlet.membership.front_controller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> map, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        //v3와 달리 ModelView를 생성하지 않고 인자로 받은 model에 데이터 보관
        model.put("members", members);

        //V3와 달리 ModelView를 사용하지 않고 View의 논리 이름을 반환
        return "members";
    }
}
