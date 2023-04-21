package Page;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
         WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
     //driver.findElement(By.id("userEmail")).sendKeys("tqa12@gmail.com");
    @FindBy(id="userEmail")
    WebElement email;

    @FindBy(id="userPassword")
            WebElement password;

    @FindBy(css=".btn-block.login-btn")
    WebElement login;

    @FindBy(css ="[class*='flyInOut']")
    WebElement errorMessage;


    public ProductCatelogPage loginApplication(String userEmail, String pwd){
        email.sendKeys(userEmail);
        password.sendKeys(pwd);
        login.click();
        //ProductCatelog productCatelogPage=new ProductCatelog(driver);
        return new ProductCatelogPage(driver);
    }

    public String getErrorMessage(){
        String errorM=errorMessage.getText();
        webElementToAppear(errorMessage);
        return errorM;

    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");


    }





}
