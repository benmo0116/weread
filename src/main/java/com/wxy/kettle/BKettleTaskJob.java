package com.wxy.kettle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author wxy
 * @create 2018-03-15 16:02
 * @desc B—Java中直接执行transformation/job
 **/
public class BKettleTaskJob {
    private static Logger LOGGER = LogManager.getLogger(BKettleTaskJob.class.getName());

    public void run() throws Exception {
        LOGGER.info("*****kettle定时任务运行开始******");
        String transFileName = "F:/ETL工具/ETLtest/TransExcelTest2.ktr";
        BKettleUtil.callNativeTrans(transFileName);
        LOGGER.info("*****kettle定时任务运行结束******");
    }

    public static void main(String[] args) throws Exception {
        BKettleTaskJob job = new BKettleTaskJob();
        job.run();
    }
}
