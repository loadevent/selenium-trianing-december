package Chapter4.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exercise4_1 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        boolean isAvailable = false;

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        WebElement lblHeading = driver.findElement(By.xpath("//h2[normalize-space()='Available Examples']"));
        isAvailable = lblHeading.getText().equals("Available Examples");
        System.out.println(isAvailable);

        driver.findElement(By.cssSelector("a[href='/dynamic_loading']")).click();

        WebElement lblLoadedElementsHeading = driver.findElement(By.cssSelector("div[class='example'] h3"));
        isAvailable = lblLoadedElementsHeading.getText().equals("Dynamically Loaded Page Elements");
        System.out.println(isAvailable);

        driver.findElement(By.cssSelector("a[href='/dynamic_loading/1']")).click();

        WebElement lblExampleHeading = driver.findElement(By.cssSelector("#content > div > h4"));
        isAvailable = lblExampleHeading.getText().equals("Example 1: Element on page that is hidden");
        System.out.println(isAvailable);

        driver.findElement(By.cssSelector("div[id='start'] button")).click();

        WebElement lblFinish = new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"finish\"]/h4")));
        isAvailable = lblFinish.isDisplayed();
        System.out.println(isAvailable);
    }
}
