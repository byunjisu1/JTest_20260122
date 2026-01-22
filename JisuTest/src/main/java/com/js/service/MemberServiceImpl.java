package com.js.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.dao.MemberDao;
import com.js.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao mDao;

	@Override
	public boolean checkLogin(String id, String pw) {
		String pwDb = mDao.selectPwById(id);
		if(pw!=null && pw.equals(pwDb)) {
			return true;
		}
		return false;
	}

	@Override
	public void insertMember(String id, String pw, String name) {
		mDao.insertMember(id, pw, name);
	}
	
	@Override
	public Map<String, Object> getProfile(String id) {
		return mDao.selectProfile(id);
	}

	@Override
	public int getPoint(String id) {
		return mDao.selectPoint(id);
	}

	@Override
	public void updatePoint(int point, String id) {
		mDao.updatePoint(point, id);
	}

	@Override
	public List<MemberDto> getMemberList() {
		return mDao.selectMemberList();
	}

	@Override
	public void updateMemberDetail(String id, String pw, String name, int point) {
		mDao.updateMemberDetail(id, pw, name, point);
	}

	@Override
	public void deleteMember(String id) {
		mDao.deleteMember(id);
	}

	@Override
	public MemberDto getMemberDetail(String id) {
		return mDao.selectMemberDetail(id);
	}

	@Override
	public int getTotalMember() {
		return mDao.selectTotalMember();
	}

	@Override
	public void updateSchedulerPoint() {
		mDao.updateSchedulerPoint();
	}
}
