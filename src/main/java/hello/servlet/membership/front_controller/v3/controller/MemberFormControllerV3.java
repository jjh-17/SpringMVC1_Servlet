package hello.servlet.membership.front_controller.v3.controller;

import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> map) {
        //v2와 달리 논리 이름을 이용하여 ModelView 반환
        //v2와 달리 뷰가 아닌 모델 반환
        return new ModelView("new-form");
    }
}
