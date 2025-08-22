package io.goorm.backend.command;

import io.goorm.backend.User;
import io.goorm.backend.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MypageUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");

            // 로그인 가드
            User loginUser = (User) request.getSession().getAttribute("user");
            if (loginUser == null) {
                request.setAttribute("error", "로그인이 필요합니다.");
                return "/login.jsp";
            }

            // 파라미터
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String newPw = request.getParameter("password"); // 비워두면 기존 유지

            if (name == null || name.isBlank()) {
                request.setAttribute("error", "이름을 입력해주세요.");
                request.setAttribute("me", loginUser);
                return "/user/mypage.jsp";
            }

            // 패치 객체
            User toUpdate = new User();
            toUpdate.setId(loginUser.getId());
            toUpdate.setName(name);
            toUpdate.setEmail(email);

            boolean changePw = (newPw != null && !newPw.isBlank());
            if (changePw) {
                // TODO: 해시가 필요하면 여기서 적용(예: BCrypt)
                toUpdate.setPassword(newPw);
            }

            // 업데이트
            UserDAO dao = new UserDAO();
            boolean ok = dao.updateUserProfile(toUpdate, changePw);

            if (ok) {
                // 세션 최신화
                loginUser.setName(name);
                loginUser.setEmail(email);
                if (changePw) loginUser.setPassword(newPw);
                request.setAttribute("success", "회원 정보가 수정되었습니다.");
            } else {
                request.setAttribute("error", "수정에 실패했습니다. 잠시 후 다시 시도해주세요.");
            }

            request.setAttribute("me", loginUser);
            return "/user/mypage.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "수정 처리 중 오류: " + e.getMessage());
            return "/user/mypage.jsp";
        }
    }
}
