package io.goorm.backend.command;

import io.goorm.backend.User;
import io.goorm.backend.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MypageViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 로그인 가드
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "/login.jsp";
        }

        // 세션이 오래됐을 수 있으니 DB에서 최신 조회
        UserDAO userDAO = new UserDAO();
        User fresh = userDAO.getUserById(loginUser.getId());
        request.setAttribute("me", fresh != null ? fresh : loginUser);

        // JSP 경로(아래 3번에서 생성)
        return "/user/mypage.jsp";
    }
}
