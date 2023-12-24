package hello.servlet.membership.front_controller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.MyView;
import hello.servlet.membership.front_controller.v4.controller.MemberFormControllerV4;
import hello.servlet.membership.front_controller.v4.controller.MemberListControllerV4;
import hello.servlet.membership.front_controller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns의 * : /membership/front-controller/v4으로 시작하는 모든 url에 대하여 service 호출
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/membership/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    //url에 따른 컨트롤러를 지정 - 매핑 정보
    public FrontControllerServletV4() {
        controllerV4Map.put("/membership/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/membership/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/membership/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        //매핑 정보 찾기
        String requestURI = req.getRequestURI(); //현재 URI 반환
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);

        //매핑 정보가 없다면 404오류 반환
        if (controllerV4 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //V3와 달리, model을 생성하여 컨트롤러에 넘긴다 ==>
        Map<String, String> map = createReqParamMap(req);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(map, model);

        //V3와 달리, 컨트롤러가 viewName을 반환 ==> ModelView를 이용한 viewName 반환 간소화
        MyView view = viewResolver(viewName);

        view.render(model, req, resp);
    }

    //View 논리 이름을 이용하여 View 생성하는 메서드
    //실제 위치가 바뀌더라도 컨트롤러는 수정할 필요 없음 ==> 이 메서드만 수정하면 됨
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //req내 모든 파라미터에 대한 정보를 반환하는 메서드
    private Map<String, String> createReqParamMap(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();

        req.getParameterNames().asIterator()
                .forEachRemaining(name -> map.put(name, req.getParameter(name)));

        return map;
    }
}
