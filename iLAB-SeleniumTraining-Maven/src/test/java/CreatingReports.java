import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CreatingReports {
    public static void main(String[] args) {

        String reportPath = "src/test/resources/reports/demo_report.html";
        //create an instance of Extent Reports
        ExtentReports extent = new ExtentReports();
        //Create a report type
        ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
        //configure the report
        report.config().setReportName("Kabelo's report");
        report.config().setDocumentTitle("Testing Report");
        report.config().setTheme(Theme.STANDARD);
        //attached the reporter to the extent report
        extent.attachReporter(report);

        //create tests
        ExtentTest test1 = extent.createTest("Test 1");
        test1.pass("Test is not complete");

        ExtentTest test2 = extent.createTest("Test 2");
        test2.log(Status.FAIL,"Test has failed")
                .log(Status.SKIP,"Test is skipped")
                .log(Status.WARNING,"This is just a warning")
                .log(Status.INFO,"Information about the test")
                .log(Status.PASS,"Test has passed");

        test2.addScreenCaptureFromPath("C:\\Users\\Kabelo Tlhape\\repos\\Selenium Projects\\December\\iLAB-SeleniumTraining-Maven\\src\\test\\resources\\screenshots\\scnshot_[20241211_095857].png");

        extent.flush();

        try {
            //open the report with you default browser
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
