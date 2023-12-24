package hello.servlet.membership.front_controller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.v1.controller.MemberFormControllerV1;
import hello.servlet.membership.front_controller.v1.controller.MemberListControllerV1;
import hello.servlet.membership.front_controller.v1.controller.MemberSaveControllerV1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns의 * : /membership/front-controller/v1으로 시작하는 모든 url에 대하여 service 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/membership/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    //url에 따른 컨트롤러를 지정 - 매핑 정보
    public FrontControllerServletV1() {
        controllerV1Map.put("/membership/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/membership/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/membership/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //매핑 정보 찾기
        String requestURI = req.getRequestURI(); //현재 URI 반환
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);

        //매핑 정보가 없다면 404오류 반환
        if (controllerV1 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //매핑 정보가 있음 ==> 각 컨트롤러의 process 실행
        controllerV1.process(req, resp);

    }
}
