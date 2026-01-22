package com.js.service;

import java.util.List;
import java.util.Map;

import com.js.dto.MemberDto;

public interface MemberService {
	
	/**
	 * checkLogin
	 * @param id : 입력한 id
	 * @param pw : 입력한 pw
	 * @return 로그인 성공(true), 실패(false)
	 */
	boolean checkLogin(String id, String pw);
	
	/**
	 * 회원가입
	 * @param id
	 * @param pw
	 * @param name
	 */
	void insertMember(String id, String pw, String name);
	
	/**
	 * 사용자 정보
	 * @param id
	 * @return id, 이름, 보유 포인트
	 */
	Map<String, Object> getProfile(String id);
	
	/**
	 * 포인트 현황
	 * @param id
	 * @return 현재 보유하고 있는 포인트 점수
	 */
	int getPoint(String id);
	
	/**
	 * 포인트 업데이트
	 * @param point
	 * @param id
	 */
	void updatePoint(int point, String id);
	
	/**
	 * 회원 전체 목록
	 * @return 회원 전체 목록(id, pw, name, point)
	 */
	List<MemberDto> getMemberList();
	
	/**
	 * 회원 정보 수정
	 * @param id
	 */
	void updateMemberDetail(String id, String pw, String name, int point);
	
	/**
	 * 회원 삭제
	 * @param id
	 */
	void deleteMember(String id);
	
	/**
	 * 회원 정보 조회
	 * @param id
	 * @return id, pw, name, point
	 */
	MemberDto getMemberDetail(String id);
	
	/**
	 * 전체 회원 수 
	 * @return 전체 회원 수 
	 */
	int getTotalMember();
	
	/**
	 * 전체 회원 1포인트 증가
	 */
	void updateSchedulerPoint();
}
