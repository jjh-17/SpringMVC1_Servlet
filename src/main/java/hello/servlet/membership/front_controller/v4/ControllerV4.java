package hello.servlet.membership.front_controller.v4;

import java.util.Map;

//컨트롤러 인터페이스
public interface ControllerV4 {
    //V3와 달리 추가 파라미터 존재, String(ViewName) 반환
    //model을 파라미터로 가짐
    String process(Map<String, String> map, Map<String, Object> model);
}
