package com.schedule.control.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class.getName());
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    private int count = 0;

    @Scheduled(fixedRateString = "${task.schedule.delay.rate}")
    public void runTask() {
        LOGGER.debug("This task is running at "+ format.format(new Date()));
        count++;

        try {
            Thread.sleep(2000);

            if (count %3 == 0) {
                throw new Exception("Unknown exception");
            }
        } catch (Exception e) {
            LOGGER.error("Exception in runTask::", e);
            return;
        }
        LOGGER.debug("This task is complete at "+format.format(new Date()));
    }
}
