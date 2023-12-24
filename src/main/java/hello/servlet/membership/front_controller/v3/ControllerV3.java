package hello.servlet.membership.front_controller.v3;

import hello.servlet.membership.front_controller.ModelView;

import java.util.Map;

//컨트롤러 인터페이스
public interface ControllerV3 {
    //V2와 달리 servlet에 종속족이지 않음, ModelView 반환
    ModelView process(Map<String, String> map);
}
