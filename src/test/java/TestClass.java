import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class TestClass {
    @Test
    public void openBrowser() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        // Instantiate a ChromeDriver class.
        WebDriver driver= new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,30);
        FileInputStream fis = new FileInputStream("C:\\MyWorkspace\\ProjectFormHimanshu\\src\\test\\Data\\dataFIle.properties");
        // create Properties class object to access properties file
        Properties prop = new Properties();
        // load the properties file
        prop.load(fis);
        // get the property of "url" using getProperty()
        System.out.println(prop.getProperty("url"));
        System.out.println(prop.getProperty("author"));
        // Launch Website
        driver.navigate().to("https://www.shopify.in/");
        //Maximize the browser
        driver.manage().window().maximize();
        String emailIdEntered="honeyarora9189@gmail.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log in') and contains(@class ,'item--user') ]")));
//        WebElement existingAccount=driver.findElement(By.cssSelector(".ui-stack-item.ui-stack-item--fill.account-card__destination .ui-heading"));
            driver.findElement(By.xpath("//a[contains(text(),'Log in') and contains(@class ,'item--user') ]")).click();
            driver.findElement(By.xpath("//input[@placeholder='myshop.myshopify.com']")).sendKeys("honey-arora.myshopify.com");
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.findElement(By.cssSelector("#account_email")).sendKeys(emailIdEntered);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
            while (driver.findElement(By.xpath("//button[@type='submit']")).isEnabled() == false) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.findElement(By.cssSelector("input[label='Password']")).sendKeys("Ahgem@123");
            while (driver.findElement(By.xpath("//button[@type='submit']")).isEnabled() == false) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement loggedInAccount=driver.findElement(By.cssSelector("p.Polaris-TopBar-UserMenu__Name_yisp9"));
        if(loggedInAccount.isDisplayed()==false) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Polaris-MessageIndicator__MessageIndicatorWrapper_152yb']")));
            driver.findElement(By.xpath("//div[@class='Polaris-MessageIndicator__MessageIndicatorWrapper_152yb']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Manage account']")));
            driver.findElement(By.xpath("//div[text()='Manage account']")).click();
            String parentWindowHandle = driver.getWindowHandle();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            for (int i = 0; i < tabs2.size(); i++) {
                while (driver.getTitle() != "Shopify account profile") {
                    driver.switchTo().window(tabs2.get(i));
                    break;
                }
            }
            String paragraph = driver.findElement(By.xpath("//div[@class='ui-banner__content-text']/p")).getText();
            System.out.println("Paragraph text is:" + paragraph);
            Assert.assertTrue(paragraph.contains(emailIdEntered), "Login validated successfully");
            driver.switchTo().window(parentWindowHandle);
        }
        //If the account gets expired
        else{
            Assert.assertTrue(loggedInAccount.getText().contains("Himanshu Arora"));
        }
        driver.quit();
    }
}
