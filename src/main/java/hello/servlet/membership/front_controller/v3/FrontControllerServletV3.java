package hello.servlet.membership.front_controller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.MyView;
import hello.servlet.membership.front_controller.v3.controller.MemberFormControllerV3;
import hello.servlet.membership.front_controller.v3.controller.MemberListControllerV3;
import hello.servlet.membership.front_controller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns의 * : /membership/front-controller/v3으로 시작하는 모든 url에 대하여 service 호출
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/membership/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    //url에 따른 컨트롤러를 지정 - 매핑 정보
    public FrontControllerServletV3() {
        controllerV3Map.put("/membership/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/membership/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/membership/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        //매핑 정보 찾기
        String requestURI = req.getRequestURI(); //현재 URI 반환
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

        //매핑 정보가 없다면 404오류 반환
        if (controllerV3 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //V2와 달리, request의 모든 파라미터에 대한 정보를 map에 저장하는 과정 추가
        Map<String, String> map = createReqParamMap(req);

        //V2와 달리, 각 컨트롤러의 호출 결과가 Model임
        ModelView modelView = controllerV3.process(map);

        //V2와 달리, Model을 이용하여 View 생성
        String viewName = modelView.getViewName(); //view의 논리 이름 get
        MyView view = viewResolver(viewName);

        //V2와 달리 렌더링에 model이 필요
        view.render(modelView.getModel(), req, resp);

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
