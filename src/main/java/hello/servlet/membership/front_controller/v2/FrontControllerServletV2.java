package hello.servlet.membership.front_controller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.MyView;
import hello.servlet.membership.front_controller.v2.controller.MemberFormControllerV2;
import hello.servlet.membership.front_controller.v2.controller.MemberListControllerV2;
import hello.servlet.membership.front_controller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns의 * : /membership/front-controller/v2으로 시작하는 모든 url에 대하여 service 호출
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/membership/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    //url에 따른 컨트롤러를 지정 - 매핑 정보
    public FrontControllerServletV2() {
        controllerV2Map.put("/membership/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/membership/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/membership/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        //매핑 정보 찾기
        String requestURI = req.getRequestURI(); //현재 URI 반환
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);

        //매핑 정보가 없다면 404오류 반환
        if (controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //V1과 달리, 각 컨트롤러의 호출 결과(View)를 이용하여 렌더링 수행
        MyView view = controllerV2.process(req, resp);
        view.render(req, resp);
    }
}
