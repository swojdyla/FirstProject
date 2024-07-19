package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SingUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Sebastian")
                .setLastName(lastName)
                .setPhone("777888999")
                .setEmail("tester" + randomNumber + "@test.com")
                .setPassword("Test123")
                .confrimPassword("Test123")
                .singUp();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Sebastian Testowy");
    }
/*    @Test
    public void signUpTest2() {

        int randomNumber = (int)(Math.random()*1000);
        String email = "tester" + randomNumber + "@test.com";

        User user = new User();
        user.setFirstName("Sebastian");
        user.setLastName("Testowy");
        user.setPhone("888999000");
        user.setEmail(email);
        user.setPassword("Test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
     // singUpPage.fillSingUpForm("Sebastian", "Testowy", "777888999", email, "Test123");
        singUpPage.fillSingUpForm(user);


        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Sebastian Testowy");
    }*/

    @Test
    public void blankRegistrationFormulaTest() {

        SingUpPage singUpPage = new HotelSearchPage(driver).openSignUpForm();
        singUpPage.singUp();

        List<String> errors = singUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void invalidEmailTest() {

        SingUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Sebastian")
                .setLastName("Testowy")
                .setPhone("777888999")
                .setEmail("email")
                .setPassword("Test123")
                .confrimPassword("Test123");

        signUpPage.singUp();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
