package vn.khanhduy.controller.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        //Nếu chưa login
        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("/login");
            return false;
        }

        String role = session.getAttribute("role").toString();
        String uri = request.getRequestURI();

        //Kiểm tra quyền
        if (uri.startsWith("/admin") && !"ADMIN".equals(role)) {
            response.sendRedirect("/login?error=unauthorized");
            return false;
        }
        if (uri.startsWith("/user") && !"USER".equals(role)) {
            response.sendRedirect("/login?error=unauthorized");
            return false;
        }

        return true; //Cho phép đi tiếp
    }
}
