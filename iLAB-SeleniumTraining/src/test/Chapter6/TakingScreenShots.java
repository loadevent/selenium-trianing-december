package Chapter6;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
//import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import com.google.common.io.*;

public class TakingScreenShots {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //enter username
        driver.findElement(By.xpath("//input[@placeholder='Username']"))
                .sendKeys("Admin");
        //enter password
        driver.findElement(By.cssSelector("input[name=password]"))
                .sendKeys("admin123");


        //Take a screenshot
        try{
            Thread.sleep(4000);
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String fileName = "_[" + timeStamp + "].png";
            //move the file to a physical location
            Files.move(sourceFile,new File("src/test/resources/screenshots/screenshot" + fileName));

        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        //click login
        driver.findElement(By.cssSelector("button[type=submit]")).click();




    }
}
