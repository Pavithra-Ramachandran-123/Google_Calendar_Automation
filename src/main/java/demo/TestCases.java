package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().browserVersion("131.0.6778.265").setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        //driver.manage().window().maximize();
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        Assert.isTrue(driver.getCurrentUrl().contains("/calendar/u/0/r"),"url doesnt move");
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        WebElement calendarView= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[following-sibling::span//i and text()]")));
        if(!calendarView.getText().equals("Month")){
            calendarView.click();
            WebElement monthElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div/ul/li[@data-viewkey='month']")));
            monthElement.click();
        }
        //
        String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        //System.out.println(date);
        List<WebElement> dateElements= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("qLWd9c")));
        for(WebElement d:dateElements){
            //System.out.println(d.getText());
            if(d.getText().contains("today")){
                //System.out.println("date clicked");
                d.click();
                break;
            }
        }   
        WebElement taskTab= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='tabTask']")));
        taskTab.click();
        WebElement titleElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Add title and time']")));
        titleElement.sendKeys("Crio INTV Task Automation");
        // WebElement descriptionClick= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='description']")));
        // descriptionClick.click();
        WebElement descriptionField= wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("textarea")));
        descriptionField.sendKeys("Crio INTV Calendar Task Automation");
        Thread.sleep(1000);
        WebElement saveButton= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'pEVtpe')]/span[text()='Save']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        //saveButton.click();
        //Thread.sleep(4000);

    }
    public  void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        driver.get(driver.getCurrentUrl());
        WebElement existingTask= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Crio INTV Task Automation']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", existingTask);
        
        WebElement editButton= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Edit task']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        WebElement editDescription= wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("textarea")));
        String description="Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
        editDescription.clear();
        editDescription.sendKeys(description);
        Thread.sleep(1000);

        WebElement saveButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span[text()='Save']]")));
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        //driver.navigate().refresh();
        // Use Actions class for a robust click
        Actions actions = new Actions(driver);
        actions.moveToElement(saveButton).click().perform();

        WebElement taskUpdated=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'nEwe6b')]/div/div[contains(text(),'Task updated')]")));
        //driver.get("https://calendar.google.com/");
        System.out.println(taskUpdated.isDisplayed());
        //Thread.sleep(10000);
        //existingTask=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Crio INTV Task Automation']")));
        driver.get(driver.getCurrentUrl());
        existingTask= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[span[contains(text(),'task:')]]")));
        existingTask.click();
        //Thread.sleep(4000);
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", existingTask);
        
        WebElement updatedDescription= wait.until(ExpectedConditions.presenceOfElementLocated(By.className("vfzv")));
    
        System.out.println(updatedDescription.getText());
        Assert.isTrue(updatedDescription.getText().contains(description), "description doesn't match");
        System.out.println("end Test case: testCase03");
    }

    public  void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");
        WebElement existingTask= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Crio INTV Task Automation']")));
        Assert.isTrue(existingTask.getText().contains("Crio INTV Task Automation"), "Title doesnt match");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", existingTask);
        
        WebElement deleteButton= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label=\"Delete task\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
        //u1KZub VUvTof qSNtwe PlAvif nEwe6b
        WebElement deletedMessagePopup=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'nEwe6b')]/div/div[contains(text(),'Task deleted')]")));
        System.out.println(deletedMessagePopup.getText());
        Assert.isTrue(deletedMessagePopup.getText().contains("deleted"), "Deleted message not displayed");
        System.out.println("end Test case: testCase04");

        
    }



}
