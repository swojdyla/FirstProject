package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SingUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    private WebDriver driver;


    public SingUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SingUpPage setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SingUpPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SingUpPage setPhone(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SingUpPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;

    }

    public SingUpPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;

    }

    public SingUpPage confrimPassword(String password) {
        confirmpasswordInput.sendKeys(password);
        return this;
    }

    public LoggedUserPage singUp() {
        signUpButton.click();
        return new LoggedUserPage(driver);
    }

    public List<String> getErrors() {
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
