package com.spring.jobScheduler;

/**
 * @author viet anh
 * Guide 
 * Cron expessions (1 2 3 4 5 6)
 * 1 : second (0-59)
 * 2 : minute (0-59)
 * 3 : hour (0-23)
 * 4 : day (1-31)
 * 5 : month (1-12 or JAN-DEC)
 * 6 : day of week (0-7 or MON_SUN)
 */
public class SchedulerExpression {
	public static final String SCHEDULER_1 = "0 0 12 * * ?"; // Chạy lúc 12h trưa hằng ngày
	public static final String SCHEDULER_2 = "0 44 14 ? * *"; // Chạy lúc 10h15 sáng hằng ngày
	public static final String SCHEDULER_4 = "0 0/1 14 * * ?"; // Chạy 5 phút một lần bắt đầu vào lúc 2 giờ chiều
	public static final String SCHEDULER_5 = "0 15 10 ? * MON-FRI"; // Chạy lúc 10 giờ 15 phút các ngày thứ 2 đến thứ 6
	public static final String SCHEDULER_6 = "0 15 10 L * ?"; // Chạy lúc 10 giờ 15 phút sáng vào ngày cuối cùng của tháng.
}