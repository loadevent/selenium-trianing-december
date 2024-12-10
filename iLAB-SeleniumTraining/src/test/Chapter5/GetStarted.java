package Chapter5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetStarted {

    @Test
    void additionTest(){
        //initialize expected results
        int num1 = 2, num2 = 3;
        int expected = 5;
//        //Act - Actual results
        int actual = Calculations.addition(num1, num2);
//
        //Assert - compare actual results with expected results
        assertEquals(expected,actual, num1 + " + " + num2 +
                " should be equals to " + (num1 + num2));
    }
}
