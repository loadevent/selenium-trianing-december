package Chapter3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ElementsLocators {
    static WebDriver driver;
    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        driver.manage().window().maximize();

        locateById();
        locateByName();
        locateByCSS();
        locateByXPath();
    }

    static void locateByXPath(){
        WebElement txtAddress = driver.findElement(By.xpath("/html/body/main/div/div/div/form/div[9]/div[1]/textarea"));
        WebElement txtName = driver.findElement(By.xpath("/html/body/main/div/div/div/form/div[1]/div/input"));
        WebElement chkHobby = driver.findElement(By.xpath("/html/body/main/div/div/div/form/div[7]/div/div/div[2]/input"));

        txtAddress.sendKeys("123 Main Street \nPretoria");
        txtName.sendKeys("Michael");
        chkHobby.click();

    }
    static void locateByCSS(){
        WebElement txtName = driver.findElement(By.cssSelector("input[id=name]"));
        WebElement txtEmail = driver.findElement(By.cssSelector("input[id=email]"));
        WebElement txtSubject = driver.findElement(By.cssSelector("input.form-control[id=subjects]"));

        txtSubject.sendKeys("Selenium Java");
        //txtName.sendKeys("David");
        txtEmail.sendKeys("training@gmail.com");

    }

    static void locateById(){
        //locate the elements
        WebElement dtpBirth = driver.findElement(By.id("dob"));
        WebElement stateOption = driver.findElement(By.id("state"));
        WebElement cityOption = driver.findElement(By.id("city"));

        dtpBirth.sendKeys("06121990");//June 12 1990 - 06121990
        dtpBirth.sendKeys(Keys.TAB);

        Select selectState = new Select(stateOption);
        Select selectCity = new Select(cityOption);

        selectState.selectByValue("Haryana");
        selectCity.selectByIndex(2);


//        WebElement txtFirstname = driver.findElement(By.id("name"));
//        WebElement txtEmail = driver.findElement(By.id("email"));
//        //interact with the elements
//        txtFirstname.sendKeys("Kabelo");
//        txtEmail.sendKeys("kabelo@gmail.com");

//        System.out.println("=================Firstname=========================");
//        System.out.println("Tag: " + txtFirstname.getTagName());
//        System.out.println("Accessible: " + txtFirstname.getAccessibleName());
//        System.out.println("Text: " + txtFirstname.getText());
//
//        System.out.println("=================Email=========================");
//        System.out.println("Tag: " + txtEmail.getTagName());
//        System.out.println("Accessible: " + txtEmail.getAccessibleName());
//        System.out.println("Text: " + txtEmail.getText());

    }

    static void locateByName(){
        WebElement rdoGender = driver.findElement(By.name("gender"));
        WebElement txtMobile = driver.findElement(By.name("mobile"));
       // WebElement txtSubject = driver.findElement(By.name("subjects"));

        rdoGender.click();
        txtMobile.sendKeys("0145684747");
        //txtSubject.sendKeys("Java");

    }
}
