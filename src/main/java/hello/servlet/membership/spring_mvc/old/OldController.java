package hello.servlet.membership.spring_mvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//과거의 컨트롤러
/*
1. 핸들러 매핑 - 핸들러 조회
    1-1. RequestMappingHandlerMapping: 어노테이션 기반의 컨트롤러인 @RequestMapping을 찾아 핸들러 탐색
    1-2. BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러 탐색 ==> 현 클래스를 찾은 방법

2. 핸들러 어댑터 조회
    2-1. RequestMappingHandlerAdapter: @RequestMapping
    2-2. HttpRequestHandlerAdapter: HttpRequestHandler 처리
    2-3. SimpleControllerHandlerAdapter: Controller 인터페이스 처리 ==> 현 방법

3. 핸들러 어댑터 실행
    3-1. dispatcher servlet이 조회한 SimpleControllerHandlerAdapter를 실행, 핸들러 정보 전달
    3-2. SimpleControllerHandlerAdapter는 OldController를 실행, 결과 반환

OldController에서 사용한 객체
    -HandlerMapping = BeanNameUrlHandlerMapping
    -HandlerAdapter = SimpleControllerHandlerAdapter
 */

@Component("/spring-mvc/old-controller") //스프링 빈 이름을 url 형식으로 등록
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

        //application.properties 내 설정 정보로 물리 위치 파악
        return new ModelAndView("new-form"); //논리 이름 전달
    }
}
