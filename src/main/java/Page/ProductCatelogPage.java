package Page;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatelogPage extends AbstractComponents {
         WebDriver driver;
    public ProductCatelogPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
// List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
    @FindBy(css=".col-lg-4")
    List<WebElement> products;

    @FindBy(css=".toast-message")
     WebElement ele;





    By productsBy=By.cssSelector(".col-lg-4");
    By addCart=By.cssSelector(".card-body button:last-of-type");
    By toastMessage=By.id("toast-container");


    //action method to get product list
    public List<WebElement> getProducts() {
        elementToAppear(productsBy);
        //calling the webelement and returning products
        return products;
    }


    //find the product
    public WebElement getProductByName(String name){
        WebElement productName=getProducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(name)).findFirst().orElse(null);
        return productName;
    }

    public void addToCart(String productName){
        getProductByName(productName).findElement(addCart).click();
        elementToAppear(toastMessage);
        waitForElementDisappear(ele);

    }
















}
