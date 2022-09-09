package com.spring.jobScheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
	

	@Scheduled(cron = SchedulerExpression.SCHEDULER_4) 
	public void clock() {
		LOGGER.info("Đã thực hiện theo đúng lịch trình");
	}
}
