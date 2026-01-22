package com.js.service;

import java.util.HashSet;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.js.common.TestJob;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	private Scheduler s = null;
	
	@Override
	public void startScheduler() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		s = sf.getScheduler();
		
		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();
		CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
											.withSchedule(CronScheduleBuilder.cronSchedule("*/20 * * * * ?"))
											.forJob(jobDetail)
											.build();
		Set<Trigger> set1 = new HashSet<>();
		set1.add(trigger);
		
		s.scheduleJob(jobDetail, set1, false);
		s.start();
	}

	@Override
	public void endScheduler() throws Exception {
		s.shutdown();
	}
}
