import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_ADD_TO_CART extends Initialize {

    @Test
    public static void connectHepsiburada(){
        driver.get(pp.readProperty("test.url"));
    }

    @Test(dependsOnMethods = "connectHepsiburada")
    public static void searchProduct() throws InterruptedException {
        CM.sendKeyXpath("searchbox.xpath", product);
    }

    @Test (dependsOnMethods = "searchProduct")
    public static void clickFirstProduct() throws InterruptedException {

        CM.normalClickXpath("firstproduct.xpath");
    }

    @Test (dependsOnMethods = "clickFirstProduct")
    public static void addToCart() throws InterruptedException {
        CM.normalClickId("addToCart.id");
    }

    @Test(dependsOnMethods = "addToCart")
    public static void goToCart() throws InterruptedException {

        CM.normalClickLinkText("goToCart.linkText");
    }

    @Test(dependsOnMethods = "goToCart")
    public static void testSearchAtCart() throws InterruptedException {
        Thread.sleep(8000);
        CM.sendKeyXpath("searchAtCart.xpath", product);
    }


    @Test (dependsOnMethods = "testSearchAtCart")
    public static void testClickFirstProduct() throws InterruptedException {

        CM.normalClickXpath("firstproduct.xpath");
    }

    @Test (dependsOnMethods = "testClickFirstProduct")
    public static void testAddToCart() throws InterruptedException {

        CM.normalClickXpath("secondproduct.xpath");
    }

    @Test (dependsOnMethods = "testAddToCart")
    public static void testCloseInsuranceScreen() throws InterruptedException {
        driver.findElement(By.linkText("Onarım paketi istemiyorum")).click();
    }

    @Test(dependsOnMethods = "testCloseInsuranceScreen")
    public static void testGoToCart() throws InterruptedException {

        CM.normalClickLinkText("goToCart.linkText");
    }

    @Test(dependsOnMethods = "testGoToCart")
    public static void checkOut(){

        Assert.assertEquals(product, CM.getElementTextXpath("cartFirst.name.xpath"));
        Assert.assertEquals(product, CM.getElementTextXpath("cartSecond.name.xpath"));
        Assert.assertNotEquals(CM.getElementTextXpath("cartFirst.merchant.xpath"), CM.getElementTextXpath("cartSecond.merchant.xpath"));

    }
}
