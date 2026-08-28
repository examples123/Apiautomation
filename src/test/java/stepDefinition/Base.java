package stepDefinition;

import LoginCredentials.LoginCred;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Base {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    String tocken="";
    private static Properties properties;
    BrowserType BT;
    Page pe;
    BrowserType BR;
    // ✅ First BeforeSuite: setup base + auth

    @BeforeSuite
    public void setupAuth() {

        RestAssured.baseURI = "https://api.eventhub.rahulshettyacademy.com";
        RestAssured.basePath = "/api";
        LoginCred loginCred = new LoginCred("carrygowtham@gmail.com","Housefull@123");
        Response  response = given().log().all()
                .contentType(ContentType.JSON)
                .body(loginCred)
                .when()
                .post("/auth/login")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

        JsonPath path = new JsonPath(response.body().asString());
        String  tocken = path.get("token");

        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.eventhub.rahulshettyacademy.com")
                .setBasePath("/api")
                .addHeader("Authorization", "Bearer " + tocken)
                .setContentType(ContentType.JSON)
                .build();

        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();



    }


    @BeforeSuite(dependsOnMethods = "setupAuth")
    public void setupTestData() throws IOException {


        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
            Playwright pw = Playwright.create();
            String browser = System.getProperty("browser") != null
                    ? System.getProperty("browser")
                    : properties.getProperty("browser");

            System.setProperty("log4j.configurationFile", "C:\\Users\\mail2\\OneDrive\\Documents\\Playwright\\TestCase\\src\\test\\resources\\log4j2.xml");

            if(browser.equals("chrome")){
                 BT= pw.chromium();

            }
          else if(browser.equals("firefox")){
              BT=pw.firefox();
            }

            Browser BR=BT.launch();
            pe=BR.newPage();
            pe.navigate(properties.getProperty("url"));
            pe.setDefaultTimeout(1000);
            PlaywrightAssertions.setDefaultAssertionTimeout(3000);

        }


        catch (IOException e) {
            e.printStackTrace();
        }


    }



}
