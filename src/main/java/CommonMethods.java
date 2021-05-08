import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonMethods extends Initialize {

    private static ProjectProperties pp = new ProjectProperties();

    public String getElementTextXpath(String testObject){
        String value = "";
        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.xpath(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            value = we.getText();
        }

        return value;
    }

    public void normalClickXpath(String testObject) throws InterruptedException {
        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.xpath(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(we));
        wait.until(ExpectedConditions.visibilityOf(we));

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            we.click();
        }
    }

    public void normalClickId(String testObject){
        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.id(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(we));
        wait.until(ExpectedConditions.visibilityOf(we));

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            we.click();
        }
    }

    public void normalClickLinkText(String testObject){

        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.linkText(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(we));
        wait.until(ExpectedConditions.visibilityOf(we));

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            we.click();
        }
    }

    public void sendKeyId(String testObject, String key){
        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.id(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            we.clear();
            we.click();
            we.sendKeys(key);
            we.sendKeys(Keys.ENTER);
        }
    }

    public void sendKeyXpath(String testObject, String key){
        String[] splitTestObject = splitTestObject(pp.readPropertyFromElements(testObject));
        WebElement we = driver.findElement(By.xpath(splitTestObject[2]));
        scrollPage(we.getLocation().x, we.getLocation().y);

        if(we.isDisplayed() || we.isEnabled() || we.isSelected()){
            we.clear();
            we.click();
            we.sendKeys(key);
            we.sendKeys(Keys.ENTER);
        }
    }

    public void scrollPage(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")");

        System.out.println("WebElement Location # X : " + x + " || Y :  " + y);
    }

    public static String[] splitTestObject(String testObject){
        System.out.println("Action Performed  -> " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                "  # Inside a Method -> " + Thread.currentThread().getStackTrace()[3].getMethodName());

        String[] sTemp = (testObject.split(","));

        System.out.println("Element Name: " + sTemp[0] + " # Selector Type: " + sTemp[1] + " # Selector: " + sTemp[2]);

        return sTemp;
    }

    public void waitForPageLoaded(){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

}
