package ezmarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FrontendController {

	@RequestMapping("/**/{path:[^\\.]+}")
	public String redirect(HttpServletRequest request) {
	    String uri = request.getRequestURI();
	    if (uri.startsWith("/ws")) {
	        return null; // WebSocket 경로는 무시
	    }
	    return "forward:/index.html";
	}

}

