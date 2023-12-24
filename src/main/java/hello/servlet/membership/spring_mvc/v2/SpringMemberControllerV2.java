package hello.servlet.membership.spring_mvc.v2;

//V1은 각 url에 따른 컨트롤러를 따로 두었지만, 이를 통합 가능

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/membership/spring-mvc/v2/members") //메서드에서 중복되는 uri 통합
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    //RequestMapping: 요청 정보 매핑 ==> 매핑된 url이 호출되면 메서드 호출
    @RequestMapping("/new-form")
    public ModelAndView processForm() {
        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        return new ModelAndView("new-form");
    }

    //RequestMapping: 요청 정보 매핑 ==> 매핑된 uri가 호출되면 메서드 호출
    @RequestMapping //클래스 단위 RequestMapping의 uri를 그대로 사용
    public ModelAndView processList() {
        List<Member> members = memberRepository.findAll();

        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", members);

        return modelAndView;
    }

    //RequestMapping: 요청 정보 매핑 ==> 매핑된 url이 호출되면 메서드 호출
    @RequestMapping("/save")
    public ModelAndView processSave(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        ModelAndView modelAndView = new ModelAndView("save-result");
        modelAndView.addObject("member", member);

        return modelAndView;
    }

}
