package Chapter3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindingElements {
    public static void main(String[] args) {

        getMultipleElements();
    }

    static void getMultipleElements(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        List<WebElement> dynamiclinks = driver.findElements(By.partialLinkText("File"));

        for (WebElement link : dynamiclinks){
            System.out.println("Text: " + link.getText());
            System.out.println("Tag: " + link.getTagName());
            System.out.println("Accessible Name: " + link.getAccessibleName());
            System.out.println("-----------------------------------------");
        }

    }

}
