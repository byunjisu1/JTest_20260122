package com.js.dao;

import java.util.List;
import java.util.Map;

import com.js.dto.MemberDto;

public interface MemberDao {
	
	/**
	 * selectPwById : 로그인 성공 여부 조회
	 * @param id : 사용자 id
	 * @return 사용자 pw
	 */
	String selectPwById(String id);
	
	/**
	 * insertMember : 회원가입
	 * @param id
	 * @param pw
	 * @param name
	 */
	void insertMember(String id, String pw, String name);
	
	/**
	 * 사용자 정보
	 * @param id
	 * @return 아이디, 이름, 포인트 점수
	 */
	Map<String, Object> selectProfile(String id);
	
	/**
	 * selectPoint : 현재 가지고 있는 포인트
	 * @param id : 사용자 아이디
	 * @return 포인트 현황
	 */
	int selectPoint(String id);
	
	/**
	 * point 업데이트
	 * @param point
	 * @param id
	 */
	void updatePoint(int point, String id);
	
	/**
	 * 회원 전체 목록
	 * @return 회원 전체 목록(id, pw, name, point)
	 */
	List<MemberDto> selectMemberList();
	
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
	MemberDto selectMemberDetail(String id);
	
	/**
	 * 전체 회원 수
	 * @return 전체 회원 수 
	 */
	int selectTotalMember();
	
	/**
	 * 전체 1 포인트 증가
	 */
	void updateSchedulerPoint();
}
