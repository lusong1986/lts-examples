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
	static int i = 0;

	@Override
	public Result run(JobContext jobContext) throws Throwable {
		try {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>start " + jobContext.getJob().getTaskId());

			BizLogger bizLogger = jobContext.getBizLogger();

			for (int j = 0; j < 1; j++) {
				bizLogger.info(">>>>>>>>>>>>>>>当前job执行进度:" + jobContext.getJob().getTaskId() + ">>>>>>>>>>>" + ++i);
				Thread.sleep(3000 );

				if (new Random().nextBoolean()) {
					throw new RuntimeException("ssssssssssss");
				}
			}

			bizLogger.error(">>>>>>>>>>>>>>>>>>>>>>>>finish");

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Run job failed!", e);
			return new Result(Action.EXECUTE_LATER, e.getMessage());
		} finally {
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			LOGGER.info(">>>>>>>>>>>>>>>>>END " + jobContext.getJob().getTaskId());
		}

		return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
	}

	@Override
	public void interrupt() {
		System.out.println(">>>>>>>>>>>我被强制中断了");

	}

}
