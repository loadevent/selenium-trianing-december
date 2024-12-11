import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import net.bytebuddy.asm.Advice;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Employee;
import utilities.Master_Data;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HRM_AddNewEmployee {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentSparkReporter report;
    private String reportPath = "src/test/resources/reports/HRM New utilities.Employee.html";

    @BeforeAll
    @DisplayName("Setup")
    void setup(){
        driver = new ChromeDriver();
        extent = new ExtentReports();
        report = new ExtentSparkReporter(reportPath);
        extent.attachReporter(report);
    }
    @Test
    @DisplayName("Open Login Page")
    @Order(1)
    void openLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Enter Login Credentials")
    @Order(2)
    void enterCredentials(){
        WebElement txtUsername = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));

        WebElement txtPassword = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name=password]")));

        txtUsername.sendKeys("Admin");
        txtPassword.sendKeys("admin123");

    }
    @Test
    @DisplayName("Click Login button")
    @Order(3)
    void login(){
        WebElement btnLogin = new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=submit]")));

        btnLogin.click();
    }

    @Test
    @DisplayName("Click PIM Menu button")
    @Order(4)
    void openPIM(){

        WebElement menuPIM = new WebDriverWait(driver,Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span")));
        menuPIM.click();

    }
    @ParameterizedTest
    @MethodSource("getEmployeeData")
    //@CsvFileSource(files = "src/test/resources/HRM New Employee Data.csv", numLinesToSkip = 999)
    @DisplayName("Enter Employee Details")
    @Order(4)
    void addNewEmployee(String firstname, String middlename, String lastname,
                        String username, String password){

        WebElement btnAddNewEmployee = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/a")));
        btnAddNewEmployee.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        //enter firstname
        driver.findElement(By.xpath("//input[@placeholder='First Name']"))
                .sendKeys(firstname);
        //enter middle name
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']"))
                        .sendKeys(middlename);
        //enter lastname
        driver.findElement(By.xpath("//input[@placeholder='Last Name']"))
                .sendKeys(lastname);
        //enable 'create login details'
        driver.findElement(By
                .xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']"))
                .click();
        //enter username
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]"))
                .sendKeys(username);
        //enter password
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/input[1]"))
                .sendKeys(password);
        //enter confirm password
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/input[1]"))
                .sendKeys(password);
        //click the save button
        driver.findElement(By.cssSelector("button[type=submit]")).click();

    }

    @Test
    @DisplayName("Confirm Employee has been added")
    @Order(6)
    void confirmNewEmployee(){

        String expected = "Personal Details";

        WebElement lblPersonalDetails = new WebDriverWait(driver,Duration.ofSeconds(8))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/h6")));

        String actual = lblPersonalDetails.getText();

        assertEquals(expected,actual);
    }
    @AfterEach
    @DisplayName("Capture Screenshot")
    void captureScreenShot(TestInfo info){
        ExtentTest reportTest = extent.createTest(info.getDisplayName());

        try {
            Thread.sleep(3000);
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(Calendar.getInstance().getTime());
            String imgName = info.getDisplayName() + "_[" + timeStamp + "].png";
            File destinationFile = new File("src/test/resources/screenshots/img" + imgName);

            Files.copy(sourceFile, destinationFile);

            reportTest.addScreenCaptureFromPath(destinationFile.getAbsolutePath());
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @AfterAll
    @DisplayName("Dispose the resources")
    void tearDown(){
        try {
            Thread.sleep(3000);
            driver.quit();
            extent.flush();
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    Stream<Arguments> getEmployeeData() {
        Employee emp = Master_Data.getDataRow();

        return Stream.of(arguments
                (emp.getFirstname(), emp.getMiddlename(), emp.getLastname(),
                emp.getUsername(), emp.getPassword()));
    }

    }
