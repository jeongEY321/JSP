package com.myweb.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class DeleteService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = ((UserVO)request.getSession().getAttribute("user")).getUserId();
		String checkPw = request.getParameter("check_pw");
		
		UserDAO dao = UserDAO.getInstance();

		response.setContentType("text/html; charset=UTF-8");
		String htmlCode;
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			if(dao.userCheck(id, checkPw) == 1) {
				dao.deleteUser(id);
				request.getSession().invalidate(); //세션에서도 지워주기
				htmlCode = "<script>\r\n"
						+ "				alert('탈퇴를 완료했습니다.');\r\n"
						+ "				location.href='/MyWeb';\r\n"
						+ "				</script>";
				
			} else {
				htmlCode = "<script>\r\n"
						+ "				alert('현재 비밀번호와 다릅니다.');\r\n"
						+ "				history.back()s;\r\n"
						+ "				</script>";

			}
			out.print(htmlCode);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		

		
		
		
		


	}

}
