package com.js.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.js.dto.MemberDto;
import com.js.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)	// "이 테스트는 스프링과 함께 실행하겠다"라고 스프링에게 알림. 만약에 이게 없다면 @Autowired 는 작동하지 않음.
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})	// 테스트 필요한 설정 파일은 root-context.xml 이다
public class MemberServiceTest {
	@Autowired
	MemberService mSvc;
	
	// checkLogin (로그인 성공 여부 체크)
	@Test
	public void testCheckLogin() throws Exception {
		// Given
		String id = "admin";
		String pw = "a";
		
		// When
		boolean result = mSvc.checkLogin(id, pw);
		
		// Then
		assertTrue("로그인 실패", result);
		System.out.println("id : " + id + ", pw : " + pw + " = " + result);
	}
	
	// insertMember
	@Test
	public void testInsertMember() throws Exception {
		// Given
		String id = "jisu2";
		String pw = "12";
		String name = "지수2";
		
		// When
		mSvc.insertMember(id, pw, name);
		
		// Then
	}
	
	// getProfile
	@Test
	public void testGetProfile() throws Exception {
		// Given
		String id = "jisu";
		
		// When
		Map<String, Object> map1 = mSvc.getProfile(id);
		
		// Then
		System.out.println(map1);
	}
	
	// getPoint
	@Test
	public void testGetPoint() throws Exception {
		// Given
		String id = "admin";
		
		// When
		int point = mSvc.getPoint(id);
		
		// Then
		System.out.println("현재 보유하고 있는 포인트 점수 : " + point);
	}
	
	// updatePoint
	@Test
	public void testUpdatePoint() throws Exception {
		// Given
		String id = "admin";
		int point = 3000;
		
		// When
		mSvc.updatePoint(point, id);
		
		// Then
	}
	
	// getMemberList
	@Test
	public void testGetMemberList() throws Exception {
		// Given
		
		// When
		List<MemberDto> listMember = mSvc.getMemberList();
		
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
		String id = "jisu2";
		String pw = "8888";
		String name = "테스트중";
		int point = 0;
		
		// When
		mSvc.updateMemberDetail(id, pw, name, point);
		
		// Then
	}
	
	// deleteMember
	@Test
	public void testDeleteMember() throws Exception {
		// Given
		String id = "jisu2";
		
		// When
		mSvc.deleteMember(id);
		
		// Then
	}
	
	// getMemberDetail
	@Test
	public void testGetMemberDetail() throws Exception {
		// Given
		String id = "jisu";
		
		// When
		MemberDto dto = mSvc.getMemberDetail(id);
		
		// Then
		System.out.println(dto.getName() + " : " + dto.getPoint());
	}
	
	// getTotalMember
	@Test
	public void testGetTotalMember() throws Exception {
		// Given
		
		// When
		int cnt = mSvc.getTotalMember();
		
		// Then
		System.out.println("전체 회원 수 : " + cnt);
	}
	
	// updateSchedulerPoint
	@Test
	public void testUpdateSchedulerPoint() throws Exception {
		// Given
		String id = "jisu";
		int point = mSvc.getPoint(id);
		
		// When
		mSvc.updateSchedulerPoint();
		int afterPoint = mSvc.getPoint(id);
		
		// Then
		assertEquals("1포인트가 적립되어야 함", point + 1, afterPoint);
		System.out.println("기존 포인트 : " + point + ", 1포인트 적립 후 : " + afterPoint);
	}
}
