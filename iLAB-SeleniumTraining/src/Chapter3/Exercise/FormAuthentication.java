package Chapter3.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
* Write a scrip that will open the URL 'https://the-internet.herokuapp.com/',
* click on the 'Form Authentication' link, complete the form using the username
* and password. Then click Login button
*
* */
public class FormAuthentication {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[21]/a")).click();

        WebElement txtUsername = driver.findElement(By.cssSelector("input[id=username]"));
        WebElement txtPassword = driver.findElement(By.cssSelector("input[id=password]"));
        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=submit]"));

        txtUsername.sendKeys("tomsmith");
        txtPassword.sendKeys("SuperSecretPassword!");
        btnLogin.click();


    }
}
