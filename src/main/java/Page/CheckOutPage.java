package Page;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents {

    WebDriver driver;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css="[placeholder='Select Country']")
    WebElement countryName;

    @FindBy(css=".ta-item:nth-of-type(2)")
    WebElement countryIndia;

    @FindBy(css=".action__submit")
    WebElement placeOrder;
    @FindBy(css=".action__submit")
    WebElement placeOrder1;

    public void placeOrder(String countrySelected){

        Actions ac=new Actions(driver);
        ac.sendKeys(countryName,countrySelected).build().perform();
        countryIndia.click();
    }
    public ConfirmationPage submitOrder(){
        clickWebElements(placeOrder);
        return new ConfirmationPage(driver);
    }

    public ConfirmationPage submitOrder1(){
        clickWebElements(placeOrder);
        System.out.println("changes done");
        return new ConfirmationPage(driver);

    }
}
