package bookstore;

import core.BaseTest;
import core.DriverManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.example.bookstore.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1, groups = {"smoke"}, description = "Test successful login with valid credentials", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginSuccessWithValidCredentials() {
        logger.info("Starting login test with valid registered user credentials");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User attempts login using valid registered account credentials");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("passwordUser"));

        logger.info("Verify user successfully logs in and is redirected to the Welcome Message page");
        Assert.assertTrue(loginPage.verifyLoginSuccess(), "User should be able to see the Welcome Message page after logging in with valid credentials");
        logger.info("testLoginSuccessWithValidCredentials executed successfully");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Test failed login with incorrect password", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginFailedWithIncorrectPassword() {
        logger.info("Starting login test with incorrect password credentials");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User attempts login using valid email and incorrect password");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("incorrectPassword"));

        logger.info("Verify user login fails and error message is displayed");
        Assert.assertTrue(loginPage.verifyLoginFailed(),"User should be able to see the error message after logging in with invalid credentials");
        logger.info("testLoginFailedWithIncorrectPassword executed successfully");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Test failed login with unregistered account", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginFailedWithUnregisteredAccount() {
        logger.info("Starting login test with unregistered account credentials");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User attempts login using unregistered account credentials");
        loginPage.login(config.getProperty("emailUnregisteredUser"), config.getProperty("incorrectPassword"));

        logger.info("Verify unregistered account login fails and error message is displayed");
        Assert.assertTrue(loginPage.verifyLoginFailed(),"User should be able to see the error message after logging in with invalid credentials");
        logger.info("testLoginFailedWithUnregisteredAccount executed successfully");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Test failed login with empty credentials", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginFailedWithEmptyCredentials() {
        logger.info("Starting login test with empty credentials");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User attempts login using unregistered account credentials");
        loginPage.login("", "");

        logger.info("Verify empty credentials login fails and error message is displayed");
        Assert.assertTrue(loginPage.verifyLoginFailed(),"User should be able to see the error message after logging in with invalid credentials");
        logger.info("testLoginFailedWithEmptyCredentials executed successfully");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Test failed login with invalid email format", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginFailedWithInvalidEmailFormat() {
        logger.info("Starting login test with invalid email format");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User attempts login using invalid email format");
        loginPage.login(config.getProperty("usernameUser"), config.getProperty("passwordUser"));

        logger.info("Verify empty credentials login fails and error message is displayed");
        Assert.assertTrue(loginPage.verifyLoginFailed(),"User should be able to see the error message after logging in with invalid credentials");
        logger.info("testLoginFailedWithInvalidEmailFormat executed successfully");
    }
}