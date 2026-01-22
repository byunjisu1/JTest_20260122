package com.js.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.js.dao.MemberDao;
import com.js.dto.MemberDto;

@RunWith(SpringJUnit4ClassRunner.class)	// "이 테스트는 스프링과 함께 실행하겠다"라고 스프링에게 알림. 만약에 이게 없다면 @Autowired 는 작동하지 않음.
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})	// 테스트 필요한 설정 파일은 root-context.xml 이다
public class MemberDaoTest {
	@Autowired
	MemberDao mDao;
	
	// selectPwById (로그인 성공 여부 체크)
	@Test
	public void testSelectPwById() throws Exception {
		// Given
		String id = "admin";
		
		// When
		String pw = mDao.selectPwById(id);
		
		// Then
		assertNotNull(pw);
		System.out.println("id : " + id + ", pw : " + pw);
	}

	// insertMember
	@Test
	public void testInsertMember() throws Exception {
		// Given
		String id = "jisu";
		String pw = "1234";
		String name = "지수";
		
		// When
		mDao.insertMember(id, pw, name);
		
		// Then
	}
	
	// selectProfile
	@Test
	public void testSelectProfile() throws Exception {
		// Given
		String id = "jisu";
		
		// When
		Map<String, Object> map1 = mDao.selectProfile(id);
		
		// Then
		System.out.println(map1);
	}
	
	// selectPoint
	@Test
	public void testSelectPoint() throws Exception {
		// Given
		String id = "admin";
		
		// When
		int point = mDao.selectPoint(id);
		
		// Then
		System.out.println("현재 보유하고 있는 포인트 점수 : " + point);
	}
	
	// updatePoint
	@Test
	public void testUpdatePoint() throws Exception {
		// Given
		String id = "admin";
		int point = 2000;
		
		// When
		mDao.updatePoint(point, id);
		
		// Then
	}
	
	// selectMemberList
	@Test
	public void testSelectMemberList() throws Exception {
		// Given
		
		// When
		List<MemberDto> listMember = mDao.selectMemberList();
		
		// Then
		assertNotNull("listMember 리스트는 null이 아니어야 함", listMember);
		for(MemberDto dto : listMember) {
			System.out.println(dto.getName() + " : " + dto.getPoint());
		}
	}
	
	// updateMemberDetail
	@Test
	public void testUpdateMemberDetail() throws Exception {
		// Given
		String id = "c";
		String pw = "9999";
		String name = "테스트중";
		int point = 0;
		
		// When
		mDao.updateMemberDetail(id, pw, name, point);
		
		// Then
	}
	
	// deleteMember
	@Test
	public void testDeleteMember() throws Exception {
		// Given
		String id = "c";
		
		// When
		mDao.deleteMember(id);
		
		// Then
	}
	
	// selectMemberDetail
	@Test
	public void testSelectMemberDetail() throws Exception {
		// Given
		String id = "jisu";
		
		// When
		MemberDto dto = mDao.selectMemberDetail(id);
		
		// Then
		System.out.println(dto.getName() + " : " + dto.getPoint());
	}
	
	// selectTotalMember
	@Test
	public void testSelectTotalMember() throws Exception {
		// Given
		
		// When
		int cnt = mDao.selectTotalMember();
		
		// Then
		System.out.println("전체 회원 수 : " + cnt);
	}
	
	// updateSchedulerPoint
	@Test
	public void testUpdateSchedulerPoint() throws Exception {
		// Given
		String id = "jisu";
		int point = mDao.selectPoint(id);
		
		// When
		mDao.updateSchedulerPoint();
		int afterPoint = mDao.selectPoint(id);
		
		// Then
		assertEquals("1포인트가 적립되어야 함", point + 1, afterPoint);
		System.out.println("기존 포인트 : " + point + ", 1포인트 적립 후 : " + afterPoint);
	}
}
