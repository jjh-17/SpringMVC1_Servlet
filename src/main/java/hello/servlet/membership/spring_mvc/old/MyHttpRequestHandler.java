package hello.servlet.membership.spring_mvc.old;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

//HttpRequestHandler: Servlet과 가장 유사한 컨트롤러
/*
1. 핸들러 매핑 - 핸들러 조회
    1-1. RequestMappingHandlerMapping: 어노테이션 기반의 컨트롤러인 @RequestMapping을 찾아 핸들러 탐색
    1-2. BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러 탐색 ==> 현 클래스를 찾은 방법

2. 핸들러 어댑터 조회
    2-1. RequestMappingHandlerAdapter: @RequestMapping
    2-2. HttpRequestHandlerAdapter: HttpRequestHandler 처리 ==> 현 방법
    2-3. SimpleControllerHandlerAdapter: Controller 인터페이스 처리

3. 핸들러 어댑터 실행
    3-1. dispatcher servlet이 조회한 SimpleControllerHandlerAdapter를 실행, 핸들러 정보 전달
    3-2. SimpleControllerHandlerAdapter는 MyHttpRequestHandler를 실행, 결과 반환

OldController에서 사용한 객체
    -HandlerMapping = BeanNameUrlHandlerMapping
    -HandlerAdapter = HttpRequestHandlerAdapter
 */
@Component("/spring-mvc/request-handler") //스프링 빈 이름을 url 형식으로 등록
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
