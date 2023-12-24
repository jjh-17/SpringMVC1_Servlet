package hello.servlet.membership.front_controller.v5;

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
import hello.servlet.membership.front_controller.v4.controller.MemberFormControllerV4;
import hello.servlet.membership.front_controller.v4.controller.MemberListControllerV4;
import hello.servlet.membership.front_controller.v4.controller.MemberSaveControllerV4;
import hello.servlet.membership.front_controller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.membership.front_controller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="fronControllerServletV5", urlPatterns = "/membership/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //매핑할 controller 버전이 다르므로 Object 사용
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);
        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //현재 핸들러의 어댑터 찾기
        MyHandlerAdapter currentAdapter = getHandlerAdapter(handler);

        //ModelView반환
        ModelView modelView = currentAdapter.handle(req, resp, handler);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        view.render(modelView.getModel(), req, resp);
    }

    //uri-handler 매핑 메서드
    private void initHandlerMappingMap() {
        //v3
        handlerMappingMap.put("/membership/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/membership/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/membership/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4
        handlerMappingMap.put("/membership/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/membership/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/membership/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    //어댑터 리스트 초기화 메서드
    private void initHandlerAdapters() {
        handlerAdapters.clear();
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    //핸들러 반환 메서드
    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);

        return handler;
    }

    //인자로 받은 handler의 어댑터를 반환
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                System.out.println("handler = " + handler);
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    //View 논리 이름을 이용하여 View 생성하는 메서드
    //실제 위치가 바뀌더라도 컨트롤러는 수정할 필요 없음 ==> 이 메서드만 수정하면 됨
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
