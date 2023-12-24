package hello.servlet.membership.spring_mvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;

import java.util.List;

/*
[RequestMapping]
 */
@Controller //스프링 MVC에서 어노테이션 기반 컨트롤러로 인식 ==> RequestMappingHandlerMapping에서 매핑 정보로 인식
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //RequestMapping: 요청 정보 매핑 ==> 매핑된 url이 호출되면 메서드 호출
    @RequestMapping("/membership/spring-mvc/v1/members")
    public ModelAndView process() {
        List<Member> members = memberRepository.findAll();

        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);

        return modelAndView;
    }
    
}
