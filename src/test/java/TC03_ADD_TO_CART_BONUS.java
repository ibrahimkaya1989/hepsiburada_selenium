import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TC03_ADD_TO_CART_BONUS extends Initialize {

    @Given("user is on Hepsiburada")
    public void user_is_on_hepsiburada() {
        driver = InitializeDriver();
        CM = new CommonMethods();
        driver.get(pp.readProperty("test.url"));
    }
    @When("search the product and click Enter key")
    public void search_the_product_and_click_enter_key() {
        CM.sendKeyXpath("searchbox.xpath", product);
    }
    @When("select the product that want to buy")
    public void select_the_product_that_want_to_buy() throws InterruptedException {
        CM.normalClickXpath("firstproduct.xpath");
    }
    @When("click to button to add into cart")
    public void click_to_button_to_add_into_cart() {
        CM.normalClickId("addToCart.id");
    }
    @When("User click the button to go to cart")
    public void user_click_the_button_to_go_to_cart() {
        CM.normalClickLinkText("goToCart.linkText");
    }
    @When("User check the product")
    public void user_check_the_product() {
        Assert.assertEquals(product, CM.getElementTextXpath("cartFirst.name.xpath"));
    }
    @When("User search the product again")
    public void user_search_the_product_again() throws InterruptedException {
        Thread.sleep(8000);
        CM.sendKeyXpath("searchAtCart.xpath", product);
    }
    @When("Select same product by another merchant")
    public void select_same_product_by_another_merchant() throws InterruptedException {
        CM.normalClickXpath("firstproduct.xpath");
    }
    @When("click to button to add into cart again")
    public void click_to_button_to_add_into_cart_again() throws InterruptedException {
        CM.normalClickXpath("secondproduct.xpath");
    }
    @When("go to the cart again")
    public void go_to_the_cart_again() {
        driver.findElement(By.linkText("Onarım paketi istemiyorum")).click();
        CM.normalClickLinkText("goToCart.linkText");
    }
    @Then("User Check the Cart")
    public void user_check_the_cart() {
        Assert.assertEquals(product, CM.getElementTextXpath("cartFirst.name.xpath"));
        Assert.assertEquals(product, CM.getElementTextXpath("cartSecond.name.xpath"));
        Assert.assertNotEquals(CM.getElementTextXpath("cartFirst.merchant.xpath"), CM.getElementTextXpath("cartSecond.merchant.xpath"));

        driver.close();
        driver.quit();
    }
}
