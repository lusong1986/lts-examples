package com.github.ltsopensource.example.spring;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.InterruptibleJobRunner;
import com.github.ltsopensource.tasktracker.runner.JobContext;

/**
 * @author Robert HG (254963746@qq.com) on 8/19/14.
 */
public class SpringAnnotationJobRunner implements InterruptibleJobRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationJobRunner.class);

	@Autowired
	SpringBean springBean;

	@Override
	public Result run(JobContext jobContext) throws Throwable {
		try {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>我要执行：" + jobContext.getJob().getTaskId());
			if (jobContext.getJob().getTaskId().endsWith("cron_one")) {
				Thread.sleep(20000L);
			}
			
			
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>getExtParams:" +  jobContext.getJob().getExtParams());

			if (jobContext.getJob().getTaskId().endsWith("cron_father")) {
				
				if(new Random().nextBoolean()){
				throw new RuntimeException("failed");
				}
				//Thread.sleep(2000L);
				// System.in.read();
			}
			//Thread.sleep(Math.abs(new Random().nextInt(5) *1000));

			// if (jobContext.getJob().getTaskId().endsWith("hello_5") ||
			// jobContext.getJob().getTaskId().endsWith("hello_real")) {
			// throw new RuntimeException("failed");
			// }
			//

			springBean.hello();

			BizLogger bizLogger = jobContext.getBizLogger();
			bizLogger.info(">>>>>>>>>>>>>>>>>>测试，业务日志啊啊啊啊啊");

			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>执行完了：" + jobContext.getJob().getTaskId());
		} catch (Exception e) {
			LOGGER.info("Run job failed!", e);
			return new Result(Action.EXECUTE_LATER, e.getMessage());
		}
		return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
	}

	@Override
	public void interrupt() {
		System.out.println(">>>>>>>>>>>我被强制中断了");

	}

}
