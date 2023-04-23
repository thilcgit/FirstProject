package Page;

import abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


    public class ConfirmationPage extends AbstractComponents {

        WebDriver driver;
        public ConfirmationPage(WebDriver driver){
            super(driver);
            this.driver=driver;
            PageFactory.initElements(driver,this);
            System.out.println("done change");

        }
        @FindBy(css=".hero-primary")
        WebElement confirmMessage;

        public String getConfirmationMsg(){
            return confirmMessage.getText();
        }

}
