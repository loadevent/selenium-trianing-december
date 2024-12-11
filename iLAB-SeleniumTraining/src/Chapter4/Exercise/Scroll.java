package Chapter4.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Scroll {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();


        try {
            Thread.sleep(6000);
            //Scroll
            Actions actions = new Actions(driver);
            WebElement lblHeading = driver.findElement(By.xpath("//a[normalize-space()='Key Presses']"));
            actions.scrollToElement(lblHeading).build().perform();

            lblHeading.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
