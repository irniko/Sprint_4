import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import scooter.Browsers;
import scooter.HomePage;
import scooter.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private static final String URL_HOME_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private final By buttonToOrder;
    private final static By BUTTON_ORDER_IN_HEADER = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[text() = 'Заказать']");
    private final static By BUTTON_ORDER_BELOW = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button[text() = 'Заказать']");
    private final By blockUserInfo = By.xpath(".//div[text() = 'Для кого самокат']/parent::div");
    private final By blockRentInfo = By.xpath(".//div[text() = 'Про аренду']/parent::div");

    public OrderTest(By buttonToOrder) {
        this.buttonToOrder = buttonToOrder;
    }

    @Parameterized.Parameters
    public static Object[][] getButtonToOrder() {
        return new Object [][] {
                {BUTTON_ORDER_IN_HEADER},
                {BUTTON_ORDER_BELOW},
        };
    }

    @Before
    public void setUp() {
        driver = Browsers.createDriver("chrome");

    }

    @Test
    public void checkOrderScooterByButtonInHeader() {

        HomePage objHomePage = new HomePage(driver);
        objHomePage.openUrl(URL_HOME_PAGE);
        if (buttonToOrder == BUTTON_ORDER_BELOW) {
            objHomePage.scrollToElement(buttonToOrder);
        }
        objHomePage.clickOnOrderButton(buttonToOrder);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.implicitWait();
        //видимость блока "Для кого самокат"
        assertTrue(driver.findElement(blockUserInfo).isDisplayed());
        //заполнение данных о юзере
        objOrderPage.fillUserInfo("Семен", "Пуговкин", "ул. Якорная, 2", "Спортивная", "89882345677");
        //клик на кнопку "Далее"
        objOrderPage.clickButtonNext();
        //видимость блока "Про аренду"
        assertTrue(driver.findElement(blockRentInfo).isDisplayed());
        //заполнение данных об аренде
        objOrderPage.fillRentInfo(/* "2.03.2024", */ "3", "чёрный жемчуг" , "нет грузового лифта");
        //клик на кнопку "Заказать"
        objOrderPage.clickButtonOrder();
        //клик подтверждения заказа
        objOrderPage.clickOnButtonConfirmationOrder();
        //проверка статуса заказа
        assertTrue(objOrderPage.checkOrderConfirmation());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
