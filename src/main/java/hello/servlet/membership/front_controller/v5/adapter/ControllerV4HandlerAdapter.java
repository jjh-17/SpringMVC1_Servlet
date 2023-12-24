package hello.servlet.membership.front_controller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hello.servlet.membership.front_controller.ModelView;
import hello.servlet.membership.front_controller.v4.ControllerV4;
import hello.servlet.membership.front_controller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //supports를 통해 한 번 거르므로 캐스팅 가능
        ControllerV4 controllerV4 = (ControllerV4) handler;

        Map<String, String> reqParamMap = createReqParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        //컨트롤러가 request내 파라미터 정보를 model에 저장
        String viewName = controllerV4.process(reqParamMap, model);

        //V4는 ModelView를 사용하지 않았으나, ModelView로 만들어서 형식을 통일
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

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
