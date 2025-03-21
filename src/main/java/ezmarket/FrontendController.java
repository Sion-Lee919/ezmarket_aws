package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping(value = {"/{path:[^\\.]*}", "/**/{path:[^\\.]*}"})
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}
