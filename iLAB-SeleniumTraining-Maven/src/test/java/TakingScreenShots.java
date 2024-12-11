import com.google.common.io.Files;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TakingScreenShots {
    WebDriver driver;
    @BeforeAll
    void setup(){
        driver = new ChromeDriver();
    }

    @Test
    @Order(1)
    void openLoginPage(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @Test
    @Order(2)
    void enterLoginCredentials(){
        //expected
        boolean expected = true;

        //Actual
        WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));

        txtUsername.sendKeys("Admin");
        txtPassword.sendKeys("admin123");

        boolean actual = txtUsername.isDisplayed() && txtPassword.isDisplayed();
        //Assert
        assertEquals(expected, actual);

    }
    @Test
    @Order(3)
    void login(){
        String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));
        btnLogin.click();

        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    @AfterEach
    void takeScreenShot(){

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            Thread.sleep(4000);
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(Calendar.getInstance().getTime());
            String fileName = "_[" + timeStamp + "].png";
            Files.move(sourceFile,new File("src/test/resources/screenshots/scnshot" + fileName));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
