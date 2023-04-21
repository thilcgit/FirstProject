package Page;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage extends AbstractComponents {
    WebDriver driver;
    public ShoppingCartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }


    @FindBy(css="button[routerlink*='cart']")
            WebElement cartView;

    @FindBy(css=".cartSection h3")
    List<WebElement> productsList;

    @FindBy(css=".totalRow button")
    WebElement checkOut;


    public boolean verifyProductDisplay(String productName){
        cartView.click();
        boolean check=productsList.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
        return check;

    }
    public CheckOutPage goToCheckOut(){
        clickWebElements(checkOut);
        return new CheckOutPage(driver);
    }







}
