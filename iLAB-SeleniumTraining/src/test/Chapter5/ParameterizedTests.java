package Chapter5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTests {

    @ParameterizedTest(name = "Even Numbers {arguments}")
    @ValueSource(ints = {12, -1, 7, 22, 56})
    void checkEvenNumbers(int number){
        //initialize expected results
        boolean expected = true;
        //Act
        boolean actual = (number %2 == 0);
        //Assert
        assertEquals(expected, actual, number + " is not an even number");
    }
     @ParameterizedTest(name = "Starts with 'M' {arguments}")
     @ValueSource(strings = {"Mike", "Caroline", "Misch", "Michael", "Thabo"})
    void startsWithM(String name){
        //expected results
         boolean expected = true;
         //Actual
         boolean actual = name.startsWith("M");
         //Assert
         assertEquals(expected, actual,name + " doesn't start with 'M'");
     }

     @ParameterizedTest
    @CsvSource(value = {"Mike,25,1.78","Kabelo,18,1.81","Jessica,36,1.76","Carol,22,1.75"})
    void stringAndInt(String name, int age, double height){
        //expected
         boolean expected = true;

         //Actual
         boolean actual = (age > 25 || height >= 1.78);

         //Assert
         assertEquals(expected, actual);
     }

     @ParameterizedTest
    @MethodSource(value = "studentMarks")
    void getStudentMarks(int testMark){
         assertTrue(testMark >= 60, "You need at least 60% to pass");
     }
     List<Integer> studentMarks(){
        return Arrays.asList(85,74,55,29,96);
     }

     //Method from a different class
     @ParameterizedTest
    @MethodSource("Chapter5.Calculations#fruit")
    void getFruit(String f){
        assertTrue(f.contains("o"), f + " doesn't contain the letter o");
     }

     @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/students.csv", numLinesToSkip = 1)
    void csv_students(String firstname, String lastname, int mark){

         System.out.println("firstname = " + firstname + ", lastname = "
                 + lastname + ", mark = " + mark);

     }
}
