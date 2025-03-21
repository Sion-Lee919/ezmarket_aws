package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({"/", "/{path:[^\\.]*}"})
    public String forwardToIndex() {
        return "forward:/index.html";
    }
    
}