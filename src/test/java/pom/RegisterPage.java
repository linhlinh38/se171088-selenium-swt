package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private WebDriver driver;

    // Declare element selector value here
    private By firsNameSelector = By.id("firstname");
    private By middleNameSelector = By.id("middlename");
    private By lastNameSelector = By.id("lastname");
    private By emailSelector = By.id("email_address");
    private By passwordSelector = By.id("password");
    private By confirmPasswordSelector = By.id("confirmation");
    private By registerBtnSelector = By.cssSelector("button[title='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Getter to return element on the page
    public WebElement firstName() {
        return driver.findElement(firsNameSelector);
    }

    public WebElement middleName() {
        return driver.findElement(middleNameSelector);
    }

    public WebElement lastName() {
        return driver.findElement(lastNameSelector);
    }

    public WebElement email() {
        return driver.findElement(emailSelector);
    }

    public WebElement password() {
        return driver.findElement(passwordSelector);
    }

    public WebElement confirmPassword() {
        return driver.findElement(confirmPasswordSelector);
    }

    public WebElement registerBtn() {
        return driver.findElement(registerBtnSelector);
    }

    public void inputFirstname(String firstName){
        firstName().sendKeys(firstName);
    }

    public void inputMiddlename(String middleName){
        middleName().sendKeys(middleName);
    }

    public void inputLastname(String lastName){
        lastName().sendKeys(lastName);
    }

    public void inputEmail(String email){
        email().sendKeys(email);
    }

    public void inputPassword(String password){
        password().sendKeys(password);
    }

    public void inputConfirmPassword(String confirmPassword){
        confirmPassword().sendKeys(confirmPassword);
    }

    public void clickOnRegisterBtn(){
        registerBtn().click();
    }

}
