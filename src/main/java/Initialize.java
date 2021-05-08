import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class Initialize {

    public static String product = "Xiaomi Redmi Note 9 Pro 128 GB (Xiaomi Türkiye Garantili)";

    protected static WebDriver driver;
    protected static ProjectProperties pp = new ProjectProperties();
    protected static CommonMethods CM = new CommonMethods();

    @BeforeSuite
    public static void SetupURL(){
        driver = InitializeDriver();
        CM = new CommonMethods();
    }


    public static WebDriver InitializeDriver(){
        System.out.println("*********************************************");
        System.out.println("Lounching Chrome Driver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        //System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        return driver;
    }


    @AfterSuite
    public static void tearDown(){
        driver.close();
        driver.quit();
    }
}
