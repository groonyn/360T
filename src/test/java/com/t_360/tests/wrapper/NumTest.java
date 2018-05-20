package com.t_360.tests.wrapper;

import java.util.List;

import static com.t_360.sample.NumTest.methodXY;
import static com.t_360.tests.utils.LogUtil.log;

public class NumTest {

    /**
     * Gets list of possible combination of alphabets for the numeric of telephone dial pad
     *
     * @param value value of a button on dial pad
     * @return {@link List<String>} of possible combination
     */
    public static List<String> getCombination(final String value) {
        log("Given input value is " + value);
        List<String> list;
        try {
            list = methodXY(value);
            log("Combination is: " + list.toString());
        } catch (Exception e) {
            log(e);
            throw e;
        }
        return list;
    }

}
