package com.js.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public String selectPwById(String id) {
		return sqlSession.selectOne("MemberMapper.getPwById", id);
	}

	@Override
	public void insertMember(String id, String pw, String name) {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", id);
		map1.put("pw", pw);
		map1.put("name", name);
		sqlSession.insert("MemberMapper.insertMember", map1);
	}

	@Override
	public Map<String, Object> selectProfile(String id) {
		return sqlSession.selectOne("MemberMapper.getProfile", id);
	}
	
	@Override
	public int selectPoint(String id) {
		return sqlSession.selectOne("MemberMapper.getPoint", id);
	}

	@Override
	public void updatePoint(int point, String id) {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("point", point);
		map1.put("id", id);
		sqlSession.update("MemberMapper.updatePoint", map1);
	}

	@Override
	public List<MemberDto> selectMemberList() {
		return sqlSession.selectList("MemberMapper.getMemberList");
	}

	@Override
	public void updateMemberDetail(String id, String pw, String name, int point) {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", id);
		map1.put("pw", pw);
		map1.put("name", name);
		map1.put("point", point);
		sqlSession.update("MemberMapper.updateMemberDetail", map1);
	}

	@Override
	public void deleteMember(String id) {
		sqlSession.delete("MemberMapper.deleteMember", id);
	}

	@Override
	public MemberDto selectMemberDetail(String id) {
		return sqlSession.selectOne("MemberMapper.getMemberDetail", id);
	}

	@Override
	public int selectTotalMember() {
		return sqlSession.selectOne("MemberMapper.getTotalMember");
	}

	@Override
	public void updateSchedulerPoint() {
		sqlSession.update("MemberMapper.updateSchedulerPoint");
	}
}
