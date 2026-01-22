package com.js.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.js.service.MemberService;

public class TestJob implements Job{
	private MemberService mSvc;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        
        mSvc = (MemberService) webContext.getBean("memberServiceImpl");
        
        if(mSvc != null) {
			int totalMemberCnt = mSvc.getTotalMember();
			
			java.util.Date now = new java.util.Date();
			
			System.out.println("Job이 실행됨 : " + now + " / " + totalMemberCnt + "명에게 포인트 부여(1점).");
			mSvc.updateSchedulerPoint();
        }
	}
}
