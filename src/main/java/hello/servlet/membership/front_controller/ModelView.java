package hello.servlet.membership.front_controller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

//V3 이상에서 사용 가능한 Model : 서블릿의 종속성 제거, View 이름 전달
@Getter @Setter
public class ModelView {
    private String viewName; //뷰 이름
    private Map<String, Object> model = new HashMap<>(); //모델

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
