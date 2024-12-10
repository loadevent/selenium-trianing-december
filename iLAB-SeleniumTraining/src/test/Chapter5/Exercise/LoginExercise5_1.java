package Chapter5.Exercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
public class LoginExercise5_1 {
    WebDriver driver;
    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @ParameterizedTest(name = "Verify Successful Login {arguments}")
    @CsvSource(value = {"Admin,admin123","Admin,pass123","TestAdmin,admin123"})
    void verifySuccessfulLogin(String username, String password){
        //Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //enter username
        driver.findElement(By.xpath("//input[@placeholder='Username']"))
                .sendKeys(username);
        //enter password
        driver.findElement(By.cssSelector("input[name=password]"))
                .sendKeys(password);
        //click login
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        //verify
        String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actual = driver.getCurrentUrl();

        assertEquals(expected,actual);


    }

    @AfterEach
    void closeDriver(){
        driver.quit();
    }

}
