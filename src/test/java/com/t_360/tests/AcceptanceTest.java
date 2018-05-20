package com.t_360.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Arrays;
import java.util.Collections;

import static com.t_360.tests.wrapper.NumTest.getCombination;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Description("Each test is for combination of input data of a deal pad values")
public class AcceptanceTest extends AcceptanceBase {

    // positive cases when we know where and who does smth, and how
    @DataProvider
    public static Object[][] correctNumber() {
        return new Object[][]{
                {"0", Collections.singletonList("0")},
                {"1", Collections.singletonList("1")},
                {"2", Arrays.asList("A", "B", "C")},
                {"9", Arrays.asList("W", "X", "Y", "Z")}
        };
    }

    @Test
    @Title("Check when input correct numeric value")
    @Severity(SeverityLevel.CRITICAL) // High
    @UseDataProvider("correctNumber")
    public void check_CorrectInputValue_Success(String number, Object expected) {
        assertThat("Result combination does not equal to expected",
                getCombination(number), equalTo(expected));
    }

    @Test(expected = NumberFormatException.class)
    @Title("Check when input a negative value from the outside of the boundary")
    @Severity(SeverityLevel.CRITICAL)
    public void check_BoundaryValues_Exception() {
        getCombination("-1");
    }

    @Test
    @Title("Check when input a positive value from the outside of the boundary")
    @Severity(SeverityLevel.CRITICAL)
    public void check_BoundaryValues_Success() {
        assertThat("Result combination should be an empty list",
                getCombination("10"), equalTo(Collections.emptyList()));
    }

    // because we do not know how it will be used and where
    @Test(expected = NumberFormatException.class)
    @Title("Check when input is an alphanumeric value")
    @Severity(SeverityLevel.NORMAL) // Normal
    public void check_AlphaNumeric_Success() {
        getCombination("3aBc");
    }

    @Test(expected = NumberFormatException.class)
    @Title("Check when input is a sequence of special characters")
    @Severity(SeverityLevel.MINOR) // Low
    public void check_SpecialCharacters_Success() {
        getCombination("!&@^");
    }

    @Test(expected = NumberFormatException.class)
    @Title("Check when input is an escape character")
    @Severity(SeverityLevel.MINOR)
    public void check_EscapeCharacter_Success() {
        getCombination("\t");
    }

    //StringIndexOutOfBoundsException -> not obvious
    @Test
    @Title("Check when input is an empty string")
    @Severity(SeverityLevel.MINOR)
    public void check_emptyString() {
        assertThat("Result combination should be an empty list",
                getCombination(""), equalTo(Collections.emptyList()));
    }
}
