import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GmailSignInTest {
    @Test
    public void signInMethod() throws InterruptedException {
        System.setProperty("webdriver.ie.driver", "C:\\Browser driver\\IEDriverServer.exe");

        // Instantiate a ChromeDriver class.
        WebDriver driver= new ChromeDriver();

        // Launch Website
        driver.navigate().to("https://accounts.google.com/signin/v2/identifier?passive=1209600&continue=https%3A%2F%2Faccounts.google.com%2F&followup=https%3A%2F%2Faccounts.google.com%2F&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

        //Maximize the browser
        driver.manage().window().maximize();
        String title=driver.getTitle();
        String emailIdEntered="honeyarora9189@gmail.com";
        List<WebElement> dynamicElement = driver.findElements(By.xpath("//div[text()='Use another account']"));


        if(dynamicElement.size()!=0) {
            driver.findElement(By.xpath("//div[text()='Use another account']")).click();
        }
        List<WebElement> dynamicElementForEmail = driver.findElements(By.xpath("//input[@type='email']"));

        if(dynamicElementForEmail.size()!=0) {
            driver.findElement(By.xpath("//input[@type='email']")).click();
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys(emailIdEntered);
            Thread.sleep(10000);
            driver.findElement(By.cssSelector("div.VfPpkd-RLmnJb")).click();

        }
        List<WebElement> dynamicElementForMoreWaysToSignIn = driver.findElements(By.xpath("//span[text()='More ways to sign in']/following-sibling::div"));
        if(dynamicElementForMoreWaysToSignIn.size()!=0) {
            driver.findElement(By.xpath("//span[text()='More ways to sign in']/following-sibling::div")).click();
        }

        driver.findElement(By.xpath("//div[text()='Enter your password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Ahgem@123456");
        driver.findElement(By.xpath("//span[text()='Next']/following-sibling::div")).click();

        driver.findElement(By.xpath("//img[@class='gb_Da gbii']")).click();
        String emailIdEncountered=driver.findElement(By.xpath("//div[@class='gb_ob']")).getText();


        Thread.sleep(10000);

        Assert.assertEquals(emailIdEncountered,emailIdEntered);

        Assert.assertEquals(driver.getTitle(),"Signup");
        //        Assert.assertEquals(title,"wfwff");
        driver.close();
    }
}
