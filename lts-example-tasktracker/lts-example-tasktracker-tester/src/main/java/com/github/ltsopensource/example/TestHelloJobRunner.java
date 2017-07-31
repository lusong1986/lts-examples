package com.github.ltsopensource.example;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

/**
 * @author Robert HG (254963746@qq.com) on 8/19/14.
 */
public class TestHelloJobRunner implements JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHelloJobRunner.class);

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        try {
            LOGGER.info(">>>>>>>>>>>我要执行：" + jobContext.getJob().getTaskId());
            BizLogger bizLogger = jobContext.getBizLogger();
            
            Thread.sleep(2000);

            LOGGER.info("zai执行：" + jobContext.getJob().getTaskId());
            bizLogger.info(">>>>>>>>>>>>>>>测试，业务日志啊啊啊啊啊");

            bizLogger.info(">>>>>>>>>>>>>>>job完成");
        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_FAILED, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }
}
