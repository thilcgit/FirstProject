package abstractcomponents;

import Page.OrderPage;
import Page.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="button[routerlink*='cart']")
    WebElement viewCart;

    @FindBy(css="[routerlink='/dashboard/myorders']")
    WebElement orders;

    public void elementToAppear(By findBy){
        WebDriverWait wWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void webElementToAppear(WebElement findBy){
        WebDriverWait wWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wWait.until(ExpectedConditions.visibilityOf(findBy));
    }



    public void waitForElementDisappear(WebElement webE){
        //Thread.sleep(1000)
        WebDriverWait wWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wWait.until(ExpectedConditions.invisibilityOf(webE));

    }

    public void clickWebElements(WebElement webE){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", webE);
    }
    public ShoppingCartPage goToCartPage(){

        clickWebElements(viewCart);
        viewCart.click();
        ShoppingCartPage sC=new ShoppingCartPage(driver);
        return sC;

    }
    public OrderPage goToOrdersPage(){

        orders.click();
        OrderPage orderHistory=new OrderPage(driver);
        return orderHistory;

    }

}
