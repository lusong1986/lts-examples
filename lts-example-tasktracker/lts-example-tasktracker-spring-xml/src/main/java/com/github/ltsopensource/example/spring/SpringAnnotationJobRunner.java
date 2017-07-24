package com.github.ltsopensource.example.spring;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import com.github.ltsopensource.tasktracker.runner.LtsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robert HG (254963746@qq.com) on 8/19/14.
 */
public class SpringAnnotationJobRunner implements JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationJobRunner.class);

    @Autowired
    SpringBean springBean;

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        try {
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>>我要执行：" + jobContext);
            Thread.sleep(1000L);

            springBean.hello();


            BizLogger bizLogger = LtsLoggerFactory.getBizLogger();
            bizLogger.info(">>>>>>>>>>>>>>>>>>测试，业务日志啊啊啊啊啊");

            LOGGER.info(">>>>>>>>>>>>>>>>>>>>>执行完了：" + jobContext);
        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }

}
