package tester.tests.testcomponents;

import Page.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderTest(HashMap<String, String> input, String password, String productName) throws IOException {
        //public void submitOrderTest(String email,String password,String productName) throws IOException {
        //navigate to product cat from landing page
        ProductCatelogPage productCatelogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
        //String productName2="iphone 13 pro";
        List<WebElement> products = productCatelogPage.getProducts();
        productCatelogPage.addToCart(input.get("product"));

        String countrySelected = "India";
        ShoppingCartPage sC = productCatelogPage.goToCartPage();
        boolean match = sC.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = sC.goToCheckOut();
        checkOutPage.placeOrder(countrySelected);
        ConfirmationPage confirm = checkOutPage.submitOrder();

        String header = confirm.getConfirmationMsg();
        Assert.assertEquals(header, "THANKYOU FOR THE ORDER.");

    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void verifyOrderList() {
        ProductCatelogPage productCatelogPage = landingPage.loginApplication("tqa12@gmail.com", "tQA@2023");
        OrderPage orderPage = productCatelogPage.goToOrdersPage();
        orderPage.verifyProducts(productName);
    }



    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> dataTest = getJsonDataToMap(System.getProperty("user.dir") + "src/test/java/datapackage/PurchaseOrder.json");
        return new Object[][]{{dataTest.get(0)}, {dataTest.get(1)}};
    }
}


/* *//*       @DataProvider
        public Object[][] getData(){
            return new Object[][] {{"tqa12@gmail.com","tQA@2023","ZARA COAT 3"},{"testmine81@gmail.com","Maliban@2021","ADIDAS ORIGINAL"},};
        }*//*
 @DataProvider
 public Object[][] getData() throws IOException {
  *//*   HashMap<String,String> hMap=new HashMap<String,String>();
     hMap.put("email","tqa12@gmail.com");
     hMap.put("password","tQA@2023");
     hMap.put("product","ZARA COAT 3");

     HashMap<String,String> hMap1=new HashMap<String,String>();
     hMap1.put("email","testmine81@gmail.com");
     hMap1.put("password","Maliban@2021");
     hMap1.put("product","ADIDAS ORIGINAL");*//*

// return list of HashMaps*/






