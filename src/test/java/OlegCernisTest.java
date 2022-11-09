import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.Executor;

public class OlegCernisTest {

    /**
     * TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     * и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */
    @Test
    public void testAHrefGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String button = "guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult2 = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(5000);
        WebElement title = driver.findElement(By.
                xpath("//div[@id='desktop-menu']//a[@ href='/guide']"));

        title.click();
        Thread.sleep(4000);

        String actualResult = driver.getTitle();
        String actualResult2 = driver.getCurrentUrl();
        Thread.sleep(1000);
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    /**
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */
    @Test

    public void testFahrenheitInCity() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult2 = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement fahrenheit = driver.findElement(
                By.xpath("//div[text()= 'Imperial: °F, mph']"));

        fahrenheit.click();
        Thread.sleep(3000);

        WebElement confirmF = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span"));

        String fahrenheitConfirm = confirmF.getText();
        String actualResult = fahrenheitConfirm.substring((fahrenheitConfirm.length() - 2));

        Assert.assertEquals(actualResult, expectedResult2);

        driver.quit();
    }

    /**
     * TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом
     * “We use cookies which are essential for the site to work.
     * We also use non-essential cookies to help us improve our services.
     * Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void confirmCookiesOnOpenWeather() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services." +
                " Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all\n" +
                "Manage cookies";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement cookies = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']" +
                        "/p[@class = 'stick-footer-panel__description']"));
        WebElement buttons = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']" +
                        "/div[@class = 'stick-footer-panel__btn-container']"));

        String actualResult = cookies.getText();
        System.out.println(actualResult);
        String actualresult2 = buttons.getText();
        System.out.print(actualresult2 + "\n");
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualresult2, expectedResult2);

        driver.quit();
    }

    /**
     * TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test

    public void confirmFAQSubmenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "FAQ\n" +
                "How to start\n" +
                "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);


        WebElement buttonDropMenu = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));

        buttonDropMenu.click();
//        Thread.sleep(3000);
        buttonDropMenu = (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'support-dropdown']")));

        WebElement supportFAQ = driver.findElement(
                By.xpath("//div//ul[@id = 'support-dropdown-menu']"));

        String actualResult = supportFAQ.getText();
        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }

    /**
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testCaptchaFailed() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String subject = "I need your support";
        String message = "Please help me.";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String newWindow = driver.getWindowHandle();

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportMenu = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        supportMenu.click();
        Thread.sleep(500);
        WebElement dropDownAskAQuetion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']"));
        dropDownAskAQuetion.click();
        Thread.sleep(5000);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!newWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.getWindowHandle();
        WebElement emailField = driver.findElement(By.id("question_form_email"));
        emailField.click();
        emailField.sendKeys(email);
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
        Thread.sleep(500);

        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@value ='Submit']"));
        buttonSubmit.click();
        Thread.sleep(500);

        WebElement reCAPTCHAFail = driver.findElement(
                By.xpath("//div[text()= 'reCAPTCHA verification failed, please try again.']"));

        String actualResult = reCAPTCHAFail.getText();
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


    /**
     * TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */

    @Test

    public void submitChangeFromFToC() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String tempF = "//div[text()= 'Imperial: °F, mph']";
        String tempC = "//div[text()= 'Metric: °C, m/s']";
        String expectedResult = "°C";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement fahrenheit = driver.findElement(
                By.xpath(tempF));

        fahrenheit.click();
        Thread.sleep(1000);

        WebElement celsius = driver.findElement(
                By.xpath(tempC));

        celsius.click();
        Thread.sleep(1000);

        WebElement confirmTemp = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span"));
        String confirmCTemp = confirmTemp.getText();
        String actualResult = confirmCTemp.substring((confirmCTemp.length() - 2));

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    /**
     * TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */

    @Test

    public void testClickToLogoAndConfirmThatLinkDidtChange() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = url;
        String l = "//li[@class ='logo']//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement logo = driver.findElement(By.xpath(l));

        logo.click();
        Thread.sleep(3000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */

    @Test
    public void testToFindRomeThroughFinder() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String wordRome = "Rome";
        String wordFind = "find";
        String expectedResult = "Rome";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement finder = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//input[@type = 'text']"));

        finder.click();
        finder.sendKeys(wordRome);
        finder.sendKeys(Keys.ENTER);

        String urlCurrent = driver.getCurrentUrl();

        Boolean actualUrl;
        if (urlCurrent.contains(wordFind) && urlCurrent.contains(wordRome)) {
            actualUrl = true;
        } else {
            actualUrl = false;
        }
        Boolean expectedurl = urlCurrent.contains(wordFind) && urlCurrent.contains(wordRome);

        Assert.assertEquals(actualUrl, expectedurl);

        String actualResult = driver.findElement(By.xpath("//input[@class]")).getAttribute("value");

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */
    @Test

    public void testToFind30ButtonsInSubmenuAPI() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\учеба\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);

        WebElement buttonAPI = driver.findElement(
                By.xpath("//div[@id ='desktop-menu']//a[@ href = '/api']"));
        buttonAPI.click();
        Thread.sleep(500);

        int actualResult = driver.findElements(
                By.xpath("//a[contains(@class,'orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
}


