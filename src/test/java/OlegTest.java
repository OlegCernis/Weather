import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OlegTest {
    //   TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"
    @Test

    public void testH2TagWhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(4000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder= 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(4000);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiseIndropdownMenu = driver.findElement(By.xpath(
                "//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));

        parisFRChoiseIndropdownMenu.click();

        WebElement h2CityCountryHeader =driver.findElement(By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();
       Assert.assertEquals(actualResult, expectedResult);



        driver.quit();
    }
    /**
     * TC_11_06
     * 1.  Открыть базовую ссылку
     * 2.  Нажать пункт меню Support → Ask a question
     * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */

    @Test

    public void testSupportWhitOutEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String subject = "I need your support";
        String message = "Please help me.";
        String expectedResult = "can't be blank";
        String newWindow = driver.getWindowHandle();

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);
        WebElement supportMenu = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();
        Thread.sleep(500);
        WebElement dropDownAskAQuetion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']" +
                        "/li/a[@href='https://home.openweathermap.org/questions']"));
        dropDownAskAQuetion.click();
        Thread.sleep(5000);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!newWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(500);

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@id = 'question_form_subject']"));

        subjectField.click();
        Thread.sleep(500);
        WebElement otherSubject = driver.findElement(
                By.xpath("//option[@value = 'Other']"));
        otherSubject.click();
        Thread.sleep(500);

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@id = 'question_form_message']"));
        messageField.sendKeys(message);
        messageField.click();
        Thread.sleep(4000);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement reCAPTCHASubmit = driver.findElement(
                By.xpath("//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));

        reCAPTCHASubmit.click();
        Thread.sleep(4000);

        driver.switchTo().window(window2);

        WebElement buttonSubmit = driver.findElement(
                By.xpath("//input[@value ='Submit']"));
        buttonSubmit.click();
        Thread.sleep(500);

        driver.quit();

    }






}
