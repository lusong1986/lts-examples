package com.github.ltsopensource.example.spring;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.RetryJobClient;
import com.github.ltsopensource.jobclient.domain.Response;

public class Test {


	public static void main(String[] args) {
		JobClient jobClient = new RetryJobClient();
		jobClient.setNodeGroup("AutoTest");
		jobClient.setClusterName("hdfax_cluster");
		jobClient.setRegistryAddress("zookeeper://172.16.57.14:42181");
		jobClient.start();

		// 提交任务
		Job job = new Job();
		job.setTaskId("AUTOTEST2017091914219");
		job.setParam("jobDate", "20170910");
		job.setParam("type", "PRODUCT_ESTABLISH");
		job.setParam("prodType", "1");
		job.setTaskTrackerNodeGroup("ftc_task_tracker");
		// job.setCronExpression("0 0/1 * * * ?");  // 支持 cronExpression表达式
		// job.setTriggerTime(new Date()); // 支持指定时间执行
		Response response = jobClient.submitJob(job);
		System.out.println(response);

	}
}
