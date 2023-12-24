package hello.servlet.membership.spring_mvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
[RequestMapping]
 */
@Controller //스프링 MVC에서 어노테이션 기반 컨트롤러로 인식 ==> RequestMappingHandlerMapping에서 매핑 정보로 인식
public class SpringMemberFormControllerV1 {

    //RequestMapping: 요청 정보 매핑 ==> 매핑된 url이 호출되면 메서드 호출
    @RequestMapping("/membership/spring-mvc/v1/members/new-form")
    public ModelAndView process() {
        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        return new ModelAndView("new-form");
    }
}
