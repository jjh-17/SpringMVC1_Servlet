package hello.servlet.membership.front_controller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.v3.ControllerV3;
import hello.servlet.membership.front_controller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        //supports를 통해 한 번 거르므로 캐스팅 가능
        ControllerV3 controllerV3 = (ControllerV3) handler;

        Map<String, String> reqParamMap = createReqParamMap(request);
        ModelView modelView = controllerV3.process(reqParamMap);

        return modelView;
    }

    //req내 모든 파라미터에 대한 정보를 반환하는 메서드
    private Map<String, String> createReqParamMap(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();

        req.getParameterNames().asIterator()
                .forEachRemaining(name -> map.put(name, req.getParameter(name)));

        return map;
    }
}
