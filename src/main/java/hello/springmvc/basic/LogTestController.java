package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 디폴트의 뷰 반환이 아닌 문자 반환설정
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); //해당 클래스 log 설정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";


        // 로그 레벨별 : 밑으로 갈수록 심각, 운영서버는 주로 info 레벨 부터
        log.trace("trace log= {}", name);
        log.debug("debug log= {}", name);
        log.info("info log= {}", name);
        log.warn("warn log= {}", name);
        log.error("error log = {}", name);

        return "ok";
    }

}
