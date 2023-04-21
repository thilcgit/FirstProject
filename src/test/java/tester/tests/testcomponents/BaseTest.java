package tester.tests.testcomponents;
import Page.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fileI = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\propertyFile.properties");
        //load a properties file
        prop.load(fileI);
        //get value by key
        String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");


        //if (browserName.equalsIgnoreCase("chrome")) {
        if (browserName.contains("chrome")) {
            ChromeOptions options=new ChromeOptions();
            //browser version 111 run purpose
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            //to remove flakiness in test and this is optional
            driver.manage().window().setSize(new Dimension(1440,900));//full screen mode

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("WebDriver.chrome.driver","C:\\Users\\REDTECH\\Desktop\\chromedriver\\chromedriver.exe");
            driver=new FirefoxDriver();
        } else {
            //code
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }

    public File getScreenshot(String testCaseName ,WebDriver driver) throws IOException {
        TakesScreenshot scr=(TakesScreenshot)driver;
        File source=scr.getScreenshotAs(OutputType.FILE);
        //File file=new File("C:\\Users\\REDTECH\\Documents\\errorFiles");
        File fr=new File(System.getProperty("user.dir")+"//Reports"+testCaseName+".png");
        FileUtils.copyFile(source,fr);
        return fr;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to String
        String jsonContent = FileUtils.readFileToString(new File(filePath));

        //String to HashMap (Jackson Datbind)
        ObjectMapper oMap = new ObjectMapper();
        List<HashMap<String, String>> data = oMap.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

        @BeforeMethod(alwaysRun = true)
        public LandingPage launchApplication () throws IOException {
            driver = initializeDriver();
            landingPage = new LandingPage(driver);
            landingPage.goTo();
            return landingPage;

        }
        @AfterMethod(alwaysRun = true)
        public void teardown () {
            driver.close();
        }
    }

