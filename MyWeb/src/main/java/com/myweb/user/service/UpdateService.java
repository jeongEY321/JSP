package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 1. form에서 넘어오는 데이터(파라미터)를 가져오기
		 2. 데이터를 UserVO로 포장하기
		 3. dao의 updateUSer()를 호출해서 회원정보 수정 진행하기
		 4. 수정된 정보로 세션 데이터를 교환(덮어 씌우기)
		 5. alert()을 이용해서 사용자에게 응답 주고 마이페이지로 이동.
		 */

	}

}
