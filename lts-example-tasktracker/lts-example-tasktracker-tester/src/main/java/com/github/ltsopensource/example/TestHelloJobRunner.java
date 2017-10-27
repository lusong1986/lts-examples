package com.github.ltsopensource.example;

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
public class TestHelloJobRunner implements InterruptibleJobRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestHelloJobRunner.class);

	private static String s = "";

	// static {
	// String msg = "hellohello" + new Random().nextFloat();
	// for (int i = 0; i < 10000; i++) {
	// s += msg;
	// }
	// }

	BizLogger bizLogger = null;

	@Override
	public Result run(JobContext jobContext) throws Throwable {
		try {
			bizLogger = jobContext.getBizLogger();

			System.out.println(">>>>>>>>>>>>>>>>>>>>>我要执行：" + jobContext.getJob().getTaskId());
			if (jobContext.getJob().getTaskId().equals("real_lusong")) {
				bizLogger.info(">>>>>>>>>>>>>>>>real_lusong start");
				Thread.sleep(20000 * 60);

				bizLogger.info(">>>>>>>>>>>>>>>>real_lusong end");
			}


			if (jobContext.getJob().getTaskId().equals("cron_quick")) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>sleeping........：" + jobContext.getJob().getTaskId());
				Thread.sleep(2000L * 10);
			} else {
				System.out.println(">>>>>>>>>>>>>>>>>>>>sleeping........：" + jobContext.getJob().getTaskId());
				Thread.sleep(2000L);
			}

			System.out.println(">>>>>>>>>>>>>>>>>>>>finish执行：" + jobContext.getJob().getTaskId());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Run job failed!" + e);
			return new Result(Action.EXECUTE_FAILED, e.getMessage());
		}

		return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
	}

	@Override
	public void interrupt() {
		LOGGER.info(">>>>>>>>>>>我被强制中断了>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>我被mmmm强制中断了>>>>>>>>>>");
	}

}
