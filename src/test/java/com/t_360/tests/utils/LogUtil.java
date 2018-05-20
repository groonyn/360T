package com.t_360.tests.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class LogUtil {

    private final static Logger logger = LoggerFactory.getLogger("TestLogger");

    /**
     * Adds the message string to Allure report for an appropriate test
     *
     * @param message message to add
     */
    @Step("{0}")
    public static void log(final String message) {
        logger.info(message);
    }

    /**
     * Logs exception message to CLI and a log file
     *
     * @param throwable exception to log
     */
    public static void log(Throwable throwable) {
        logger.error(String.format("%s: %s",
                throwable.getClass().getSimpleName(),
                throwable.getMessage()));
    }
}
