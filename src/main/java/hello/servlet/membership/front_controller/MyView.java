package hello.servlet.membership.front_controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

//V2 이상부터 사용하는 뷰 객체 : 컨트롤러에서 뷰로 이동하는 코드 전담
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }

    //v3 이상에서 사용하는 rendering 메서드
    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        model.forEach((key, value) -> req.setAttribute(key, value));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }
}
