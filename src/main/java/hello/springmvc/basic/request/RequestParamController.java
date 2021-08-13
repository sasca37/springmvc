package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //http://localhost:8080/request-param-v1?username=hello&age=20
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {},", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 클래스 레벨이 @Controller 인데 반환값을 데이터로 하고 싶을 때
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {},", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3") //변수 이름이 같으면 name = 생략 가능
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {},", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")  //String, int , Integer 등 단순 타입이면 어노테이션도 생략 가능
    public String requestParamV4(
            String username,
            int age
    ) {
        log.info("username = {}, age = {},", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required") //required 가 true이면 무조건 있어야한다. 디폴트 true
    public String requestParamRequired(
            @RequestParam(required = true) String username, //필수 값이여도 ""인경우는 빈문자로들어와서 ok, 아예없으면 배드리퀘스트 주의
            @RequestParam(required = false) Integer age //Integer는 객체이기때문에 Null 가능, int 는 널일 수 없어서 객체로 선언
    ) {
        log.info("username = {}, age = {},", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default") // defaultValue를 사용하면 사실상 required가 필요없다. 비어있으면 채워주기 때문에 "" 경우도 기본값으로 변경처리
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username = {}, age = {},", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {},", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username = {}, age = {},", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("username = {}, age = {},", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
