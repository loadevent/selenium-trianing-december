package Chapter2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class GetStarted {
    public static void main(String[] args) {
        //Declare and initialize a web driver object
        WebDriver driver = new ChromeDriver();
        //open a URL
        driver.get("https://www.selenium.dev/projects/");
        //maximize the browser
        driver.manage().window().maximize();

        System.out.println("Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());
        System.out.println("===========================================");
        //navigate to a different URL
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.ilabquality.com/");
        driver.navigate().to("https://www.ilabquality.com/training/");

        System.out.println("Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());
        System.out.println("===========================================");

        driver.navigate().back();

        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
}
