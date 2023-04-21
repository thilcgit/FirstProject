package tester.tests.testcomponents;

import Page.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.findElement(By.xpath("//a[text()='Register here']")).click();
        /*driver.findElement(By.xpath("//a[@class='text-reset']")).click();
        driver.findElement(By.id("firstName")).sendKeys("Thilie");
        driver.findElement(By.id("lastName")).sendKeys("QAtester");
        driver.findElement(By.id("userEmail")).sendKeys("tqa12@gmail.com");
        WebElement ab = driver.findElement(By.cssSelector("[formcontrolname='occupation']"));
        Select occ = new Select(ab);
        occ.selectByVisibleText("Engineer");
        driver.findElement(By.cssSelector("input[value='Female']")).click();
        driver.findElement(By.id("userMobile")).sendKeys("1123456789");
        driver.findElement(By.id("userPassword")).sendKeys("tQA@2023");
        driver.findElement(By.id("confirmPassword")).sendKeys("tQA@2023");
        driver.findElement(By.cssSelector("[formcontrolname='required']")).click();
        driver.findElement(By.id("login")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();*/

        LandingPage lp=new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("tqa12@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("tQA@2023");
        driver.findElement(By.cssSelector(".btn-block.login-btn")).click();

        WebDriverWait wWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
        List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));


       /* for(WebElement pro:products){
            if(pro.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("iphone 13 pro")){
                pro.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
            }
        }*/
        String productName="ZARA COAT 3";
        WebElement pr=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        pr.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".toast-message"))));


        products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("iphone 13 pro"));
        pr.findElement(By.xpath("(//button[@class='btn w-10 rounded'])[3]")).click();

        wWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".toast-message"))));

        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> productNa=driver.findElements(By.cssSelector(".cartSection h3"));
        //putting validation
        boolean check=productNa.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(check);
        //driver.findElement(By.cssSelector("li button[class='btn btn-primary']:last-of-type")).click();
        String text=driver.findElement(By.cssSelector(".totalRow button")).getText();
        System.out.println(text);
        driver.findElement(By.cssSelector(".removeWrap button")).click();
       // driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions ac=new Actions(driver);
        ac.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        wWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group.ng-star-inserted")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

        WebElement l = driver.findElement(By.cssSelector(".action__submit"));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", l);


        String header=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(header,"THANKYOU FOR THE ORDER.");














    }
}
