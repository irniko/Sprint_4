import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import scooter.Browsers;
import scooter.HomePage;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private static final String URL_HOME_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private final String questionText;
    private final String answerText;
    private final By homeFAQ = By.className("Home_FAQ__3uVm4");

    public FAQTest(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object [][] getQuestionAndAnswerText() {
        return new Object [][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
        };
    }

    @Before
    public void setUp() {
        driver = Browsers.createDriver("chrome");
    }

    @Test
    public void checkAccordionHeader() {

        HomePage objHomePage = new HomePage(driver);
        objHomePage.implicitWait();
        objHomePage.openUrl(URL_HOME_PAGE);
        // прокрутка к элементу
        objHomePage.scrollToElement(homeFAQ);

        String text = objHomePage.checkQuestionAndAnswer(questionText, answerText);
        assertEquals(answerText, text);
        System.out.println("Тест ответа соответствует вопросу");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}



