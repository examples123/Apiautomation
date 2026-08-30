package stepDefinition;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    public static Playwright pw;
    public static Browser browserInstance;
    public static Page page;
    public static Properties properties;
    public static String browser;
    public static BrowserContext context;
    @BeforeAll
    public static void beforeScenario() {

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);

            browser = System.getProperty("browser") != null
                    ? System.getProperty("browser")
                    : properties.getProperty("browser");

            String log4jPath = System.getProperty("user.dir") + "/src/test/resources/log4j2.xml";
            System.setProperty("log4j.configurationFile", log4jPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Before
    public static void before() {

        pw = Playwright.create();

        BrowserType bt;
        if (browser.equalsIgnoreCase("chrome")) {
            bt = pw.chromium();
        } else if (browser.equalsIgnoreCase("firefox")) {
            bt = pw.firefox();
        } else {
            bt = pw.chromium(); // default fallback
        }

        browserInstance = bt.launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browserInstance.newContext();
        page = context.newPage();
        page.navigate(properties.getProperty("url"));
        page.setDefaultTimeout(1000);
        PlaywrightAssertions.setDefaultAssertionTimeout(13000);
        page.setDefaultTimeout(13000);
        page.setDefaultTimeout(10000);

    }

    @After
    public static void afterScenario() {

        page.close();
        browserInstance.close();
        pw.close();

    }




}
