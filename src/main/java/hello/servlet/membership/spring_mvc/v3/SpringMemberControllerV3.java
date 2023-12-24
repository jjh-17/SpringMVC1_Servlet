package hello.servlet.membership.spring_mvc.v3;

//V1은 각 url에 따른 컨트롤러를 따로 두었지만, 이를 통합 가능

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import hello.servlet.membership.domain.member.Member;
import hello.servlet.membership.domain.repository.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/membership/spring-mvc/v3/members") //메서드에서 중복되는 uri 통합
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String processForm() {
        //V2와 달리 String 반환
        return "new-form";
    }

    @GetMapping
    public String processList(Model model) { //모델을 파라미터로 받을 수 있음
        List<Member> members = memberRepository.findAll();

        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        model.addAttribute("members", members);

        return "members";
    }

    @PostMapping("/save")
    public String processSave(@RequestParam("username") String username, //request.getParameter("username")을 매개변수에서
                              @RequestParam("age") int age,
                              Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        //ModelAndView: 모델과 뷰 정보를 담아서 반환
        model.addAttribute("member", member);

        return "save-result";
    }

}
