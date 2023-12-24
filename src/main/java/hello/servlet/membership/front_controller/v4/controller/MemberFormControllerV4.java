package hello.servlet.membership.front_controller.v4.controller;

import hello.servlet.membership.front_controller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> map, Map<String, Object> model) {
        //V3와 달리 ModelView를 사용하지 않고 View의 논리 이름을 반환
        return "new-form";
    }
}
