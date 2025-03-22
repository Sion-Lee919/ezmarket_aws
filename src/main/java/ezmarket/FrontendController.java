package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping(value = {
            "/", 
            "/{x:^(?!ws$|api$|login$|chat$).*$}/**" // ws, api, login, chat 제외
    })
    public String forward() {
        return "forward:/index.html";
    }
}


