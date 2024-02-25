package scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OrderPage {

    private final By nameLocator = By.xpath(".//input[@placeholder = '* Имя']");
    private final By surnameLocator = By.xpath(".//input[@placeholder = '* Фамилия']");
    private final By addressLocator  = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    private final By metroLocator = By.xpath(".//input[@placeholder = '* Станция метро']");
    private final By telephonNumberLocator = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    private final By dateRentLocator = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private final By arrowOfListIntervalsRent = By.xpath(".//span[@class = 'Dropdown-arrow']");
    private final By blackColourScooter = By.xpath(".//div[@class = 'Order_Checkboxes__3lWSI']/label/input[@id = 'black']");
    private final By greyColourScooter = By.xpath(".//div[@class = 'Order_Checkboxes__3lWSI']/label/input[@id = 'grey']");
    private final By commentToCourierLocator = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");

    private final By headerOrderInfo = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ' and text() = 'Заказ оформлен']");
    private final By buttonNext = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[text() = 'Далее']");

    private final By buttonOrder = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private final By buttonConfirmationOrder = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Да']");

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void implicitWait() { //неявное ожидание
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    public OrderPage clickButtonNext() {
        driver.findElement(buttonNext).click();
        return this;
    }
    public OrderPage clickButtonOrder() {
        driver.findElement(buttonOrder).click();
        return this;
    }

    public OrderPage fillUserInfo(String name, String surname, String address, String metro, String telephonNumber) {

        // имя
        driver.findElement(nameLocator).sendKeys(name);
        // фамилия
        driver.findElement(surnameLocator).sendKeys(surname);
        // адресс
        driver.findElement(addressLocator).sendKeys(address);
        // метро
        driver.findElement(metroLocator).sendKeys(metro);
        driver.findElement(By.className("select-search__select")).click();
        // телефон
        driver.findElement(telephonNumberLocator).sendKeys(telephonNumber);

        return this;
    }

    public OrderPage fillRentInfo(/* String dateRent, */ String numberOfElementOfIntervalRent, String colourScooter, String commentToCourier) {

        // дата
        //driver.findElement(dateRentLocator).sendKeys(dateRent);
        driver.findElement(dateRentLocator).click();
        driver.findElement(By.xpath(".//button[text() = 'Next Month']")).click();
        driver.findElement(By.xpath(".//div[@class = 'react-datepicker__month']/div[2]/div[1]")).click();

        // срок аренды
        driver.findElement(arrowOfListIntervalsRent).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option'][" + numberOfElementOfIntervalRent + "]")).click();

        // цвет самоката
        if (Objects.equals(colourScooter, "чёрный жемчуг")) {
            if (!driver.findElement(blackColourScooter).isSelected() ) {
                driver.findElement(blackColourScooter).click();
            }
        }
        if (Objects.equals(colourScooter, "серая безысходность")) {
            if (!driver.findElement(greyColourScooter).isSelected() ) {
                driver.findElement(greyColourScooter).click();
            }
        }
        // комментарий для курьера
        driver.findElement(commentToCourierLocator).sendKeys(commentToCourier);
        return this;
    }

    public OrderPage clickOnButtonConfirmationOrder() {
        driver.findElement(buttonConfirmationOrder).click();
        return this;
    }

    public boolean checkOrderConfirmation() {
        boolean isOrderComfirm = driver.findElement(headerOrderInfo).isDisplayed();
        System.out.println(driver.findElement(headerOrderInfo).getText().split("\\r?\\n")[0]);
        return isOrderComfirm;
    }

}
