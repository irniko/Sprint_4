package scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private final By quastionTextLocator = By.xpath(".//div[@class = 'accordion__button']");
    private final By answerTextLocator = By.xpath(".//div[@class = 'accordion__panel']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void implicitWait() { //неявное ожидание
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public HomePage openUrl(String url) {
        driver.get(url);
        return this;
    }

    public HomePage scrollToElement(By elementLocator) {
        WebElement elementToScroll = driver.findElement(elementLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToScroll);
        return this;
    }

    public String checkQuestionAndAnswer(String questionText, String answerText) {

        List<WebElement> elements = driver.findElements(By.xpath(".//div[@class = 'accordion__item']"));
        for (int i = 0; i < 7; i++) {
            if (Objects.equals(elements.get(i).findElement(quastionTextLocator).getText(), questionText)) {
                //найти локатор ответа из этого вопроса
                elements.get(i).findElement(quastionTextLocator).click();
                return elements.get(i).findElement(answerTextLocator).getText();
            }
        }
        return "";
    }

    public HomePage clickOnOrderButton(By OrderButton) {
        driver.findElement(OrderButton).click();
        return this;
    }

}

