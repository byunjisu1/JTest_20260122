package com.js.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.js.dto.MemberDto;
import com.js.service.MemberService;
import com.js.service.SchedulerService;

@Controller
public class HomeController {
	@Autowired
	MemberService mSvc;
	@Autowired
	SchedulerService scheSvc;
	
	@RequestMapping( "/Login")
	public String home(@ModelAttribute("msg") String msg) {
		return "Login";
	}
	
	@RequestMapping("/LoginAction")
	public String loginAction(String id, String pw, HttpSession session, RedirectAttributes rttr) {
		boolean result = mSvc.checkLogin(id, pw);
		
		if(result) {
			session.setAttribute("loginId", id);
			rttr.addFlashAttribute("msg", "로그인되었습니다.");
			if(session.getAttribute("loginId").equals("admin")) {
				return "redirect:/Admin";
			}
			return "redirect:/Main";
		}
		rttr.addFlashAttribute("msg", "아이디/비밀번호를 다시 확인하세요");
		return "redirect:/Login";
	}
	
	@RequestMapping("/Logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.removeAttribute("loginId");
		rttr.addFlashAttribute("msg", "로그아웃 되었습니다.");
		return "redirect:/Login";
	}
	
	@RequestMapping( "/Join")
	public String join() {
		return "Join";
	}
	
	@RequestMapping( "/Main")
	public String main(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginId");
		Map<String, Object> profileDetail = mSvc.getProfile(id);
		
		model.addAttribute("profileDetail", profileDetail);
		return "Main";
	}
	
	@RequestMapping( "/Admin")
	public String admin(HttpSession session, Model model, @ModelAttribute("msg") String msg) {
		String id = (String)session.getAttribute("loginId");
		if(id!=null && !id.equals("admin")) return "forward:/Main";
		
		List<MemberDto> listMember = mSvc.getMemberList();
		model.addAttribute("listMember", listMember);
		return "Admin";
	}
	
	@RequestMapping("/Start")
	public String start() {
		try {
			System.out.println("<<< 포인트 스케줄러가 시작되었습니다. >>>");
			scheSvc.startScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Admin";
	}
	
	@RequestMapping("/End")
	public String end() {
		try {
			System.out.println("<<< 포인트 스케줄러의 실행이 종료되었습니다. >>>");
			scheSvc.endScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Admin";
	}
	
	@RequestMapping("/MemberModify")
	public String memberModify(String id, Model model) {
		MemberDto memberDetail = mSvc.getMemberDetail(id);
		model.addAttribute("memberDetail", memberDetail);
		return "MemberModify";
	}
	
	@RequestMapping("/ModifyAction")
	public String modifyAction(String id, String pw, String name, int point, RedirectAttributes rttr) {
		mSvc.updateMemberDetail(id, pw, name, point);
		rttr.addFlashAttribute("msg", "수정되었습니다.");
		return "redirect:/Admin";
	}
}
