package stepDefinition;


import BastestClassasa.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.CreateAlertAPI.CreateDataForCreateAlert;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.restassured.RestAssured.given;



public class StepDefinitionCreatAlert extends Base  {
    RequestSpecification  req;
      Response response;
    @Given("Add create Alert PayLoad")
    public void add_create_alert_pay_load()  {
        setupAuth();

        req = given().log().all().spec(reqSpec).body(CreateDataForCreateAlert.CreateEventJsonTestdata());
        System.out.println("success");
    }

    @When("user calls  Create Alert API post Http Request")
    public void user_calls_create_alert_api_post_http_request() {

        response= req.when().post("/events").then().spec(resSpec)
                .extract().response();

    }
    @Then("Api call is success with statuscode {int}")
    public void api_call_is_success_with_statuscode(Integer int1) {

        //Assert.assertEquals(response.getStatusCode(),201);
    }
    @Then("{string} in responseBody  is {string}")
    public void in_response_body_is(String Key, String value) {

    String res=response.asString();
    //JsonPath path=new JsonPath(response.asString());
    JsonPath path=response.jsonPath();
   String actualvalue=path.getString(Key);
    Assert.assertEquals(actualvalue,value);

    }



    @When("Add launchthebrowser")
    public void  launchthebrowser() throws IOException {
        setupTestData();
        pe.getByLabel("email").fill("carrygowtham@gmail.com");
        pe.getByLabel("password").fill("Housefull@123");
        pe.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In")).click();

        assertThat(pe.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();

        pe.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
    }

    @When("User Create the event")
    public void  User_Create_the_event()
    {
        pe.locator("#event-title-input").fill("TestEvent");
        pe.locator("#admin-event-form textarea").fill("TestEvent Test");
        pe.getByLabel("category").selectOption("Sports");//imp
        pe.waitForTimeout(10000); // waits 10 seconds
        pe.getByLabel("city").fill("New York");
        pe.getByLabel("venue").fill("US");
        pe.getByLabel("Event Date & Time").fill("2026-10-22T16:16");
        pe.waitForTimeout(10000); // waits 10 seconds

        pe.getByLabel("Event Date & Time").fill("2026-10-22T16:16");
        pe.getByLabel("Price ($)").fill("100");
        pe.getByLabel("Total Seats").fill("50");
        pe.locator("#add-event-btn").click();
        // Java
        assertThat(pe.getByText("Event created!")).isVisible();
        pe.locator("#nav-events").click();

        pe.waitForTimeout(10000);

        Locator Lo  =pe.getByTestId("event-card");
        Locator Lo1= Lo.filter(new Locator.FilterOptions().setHasText("TestEvent"));

        assertThat(Lo1).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(7000));

        String seats=Lo1.getByText("seats").innerText();
        System.out.println(seats);

        Lo1.getByRole(AriaRole.LINK,
                new Locator.GetByRoleOptions().setName("Book Now")
        ).click();
        pe.waitForTimeout(10000);
        //pe.locator("#nav-events").click(new Locator.ClickOptions().setTimeout(1000));
        pe.getByLabel("Full Name").fill("John");
        pe.getByPlaceholder("you@email.com").fill("John@gmail.com");
        pe.getByPlaceholder("+91 98765 43210").fill("9121942816");
        pe.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Confirm Booking")).click();
        pe.waitForTimeout(10000);
    }

    @Then("Verify the event name")
    public void  Verifytheeventname()
    {
        assertThat(pe.getByText("Your tickets are reserved.")).isVisible();

        String text = pe.locator(".booking-ref").innerText();

        System.out.println(text);
        pe.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("View My Bookings")).click();

        Locator l1=pe.locator(".booking-ref");

        Locator l2 =l1.filter(new Locator.FilterOptions().setHasText(text));

        assertThat(l2).isVisible();
        pe.waitForTimeout(10000);



    }


}
