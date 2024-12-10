package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitStrategies {
    static WebDriver driver;
    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        //theImplicitWait();
        //theExplicitWait();
        theFluentWait();
    }

    static void theFluentWait(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));

        txtUsername.sendKeys("Admin");
        txtPassword.sendKeys("admin123");

        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));

        btnLogin.click();
    }

    static void theImplicitWait(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        txtUsername.sendKeys("Admin");
        txtPassword.sendKeys("admin123");
        btnLogin.click();
    }

    static void theExplicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name=username]")))
                .sendKeys("Admin");

        By txtPassword = By.cssSelector("input[name=password]");
        wait.until(ExpectedConditions.presenceOfElementLocated(txtPassword))
                .sendKeys("admin123");

        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));
        btnLogin.click();

    }
}
