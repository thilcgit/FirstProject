package tester.tests.testcomponents;

import Page.ProductCatelogPage;
import Page.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tester.tests.testcomponents.BaseTest;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups={"errorTest"},retryAnalyzer = Retry.class)
    public void loginErrorTest() throws IOException, InterruptedException {
        //navigate to product cat from landing page
        landingPage.loginApplication("tqa123@gmail.com", "tQA1@2020");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }

    @Test
    public void submitOrderErrorTest() throws IOException {

        ProductCatelogPage productCatelogPage = landingPage.loginApplication("tqa12@gmail.com", "tQA@2023");
        String productName = "ZARA COAT 3";
        String secondProduct="FROCK 12";
        productCatelogPage.addToCart(productName);
        String countrySelected = "India";
        //ShoppingCartPage sC = productCatelogPage.goToCartPage();
        ShoppingCartPage sC=productCatelogPage.goToCartPage();
        boolean match = sC.verifyProductDisplay(secondProduct);
        Assert.assertFalse(match);


    }

}


