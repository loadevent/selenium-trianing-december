package Chapter3.Exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class HRMLogin {

    static WebDriver driver;
    public static void main(String[] args) {

        tc1_validLogin("Admin","admin123");
        tc2_validUsernameInvalidPassword("Admin","pass123");
        tc3_invalidUsernameValidPassword("AdminTester","admin123");

    }

    static void tc1_validLogin(String username, String password){

        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement txtUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    static void tc2_validUsernameInvalidPassword(String username, String password){

        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement txtUsername = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    static void tc3_invalidUsernameValidPassword(String username, String password){

        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement txtUsername = driver.findElement(By.cssSelector("input[name=username]"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
}
