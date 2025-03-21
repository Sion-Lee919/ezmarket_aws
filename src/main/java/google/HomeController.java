package google;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public RedirectView home(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            logger.info("User is null, redirecting to login page.");
            return new RedirectView("http://13.208.47.23:8911/login");
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String provider = "unknown";

        if (authentication instanceof OAuth2AuthenticationToken) {
            provider = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        }
        
        System.out.println(" HomeController에서 saveUser() 호출");
        userService.saveUser(user, provider);
        return new RedirectView("http://13.208.47.23:8911/home"); // 로그인 후 리디렉트
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authenticated"));
        }
        
        System.out.println(" HomeController에서 saveUser() 호출");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registrationId = "unknown";

        if (authentication instanceof OAuth2AuthenticationToken) {
            registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        }

        System.out.println("로그인 : " + registrationId);//구분추가
        
        if ("google".equals(registrationId)) {
            Map<String, Object> userInfo = Map.of(
                    "authenticated", true,
                    "provider", registrationId,// 구분추가
                    "name", user.getAttribute("name"),
                    "email", user.getAttribute("email"),
                    "picture", user.getAttribute("picture")
            );
            return ResponseEntity.ok(userInfo);
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> userInfo = Map.of(
                    "authenticated", true,
                    "provider", registrationId,
                    "name", user.getAttribute("name"),
                    "email", user.getAttribute("email"),
                    "picture", user.getAttribute("profile_image")
            );
            return ResponseEntity.ok(userInfo);
        } else if ("kakao".equals(registrationId)){
            Map<String, Object> userInfo = Map.of(
                    "authenticated", true,
                    "provider", registrationId,
                    "name", user.getAttribute("name"),
                    "email", user.getAttribute("email"),
                    "picture", user.getAttribute("profile_image")
            );
            return ResponseEntity.ok(userInfo);
        } else if ("github".equals(registrationId)) {
            userService.saveUser(user, "github");
            String email = user.getAttribute("email") != null ? user.getAttribute("email") : "no-email@example.com";
            Map<String, Object> userInfo = Map.of(
                    "authenticated", true,
                    "provider", registrationId,
                    "name", user.getAttribute("login"),
                    "email", email,
                    "picture", user.getAttribute("avatar_url")
            );
            return ResponseEntity.ok(userInfo);
        
        } else {
        	return null;
        }
    }
}

