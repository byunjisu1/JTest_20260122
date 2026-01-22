package com.js.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.service.MemberService;

@RestController
public class AjaxController {
	@Autowired
	MemberService mSvc;
	
	// 회원가입 처리
	@RequestMapping("/insert_member")
	public Map<String, String> insertMember(@RequestBody Map<String, String> reqMap) {
		Map<String, String> retMap = new HashMap<>();
		
		String id = reqMap.get("id");
		String pw = reqMap.get("pw");
		String name = reqMap.get("name");
		
		mSvc.insertMember(id, pw, name);
		
		return retMap;
	}
	
	// 컨텐츠 구입
	@RequestMapping("/buy_content")
	public Map<String, Object> buyContent(@RequestBody Map<String, String> reqMap) {
		Map<String, Object> retMap = new HashMap<>();
		
		String id = reqMap.get("id");
		int needPoint = Integer.parseInt(reqMap.get("needPoint"));
		int havePoint = mSvc.getPoint(id);
		
		if(havePoint >= needPoint) {	/* 구매 성공 */
			int updatePoint = havePoint - needPoint;
			mSvc.updatePoint(updatePoint, id);
			
			retMap.put("result", true);
			retMap.put("minusPoint", updatePoint);
		} else {	/* 구매 실패 */
			retMap.put("result", false);
		}
		
		return retMap;
	}
	
	// 포인트 적립
	@RequestMapping("/click_ad")
	public Map<String, Object> clickAd(@RequestBody Map<String, String> reqMap) {
		Map<String, Object> retMap = new HashMap<>();
		
		String id = reqMap.get("id");
		int havePoint = mSvc.getPoint(id);
		
		int addPoint = (int)(Math.random()*1000) + 1;
		int updatePoint = havePoint + addPoint;
		
		mSvc.updatePoint(updatePoint, id);
		
		retMap.put("addPoint", addPoint);
		retMap.put("plusPoint", updatePoint);
		
		return retMap;
	}
	
	// 회원 삭제
	@RequestMapping("/member_delete")
	public Map<String, String> deleteMember(@RequestBody Map<String, String> reqMap) {
		Map<String, String> retMap = new HashMap<>();
		
		String id = reqMap.get("id");
		mSvc.deleteMember(id);
		
		return retMap;
	}
}
