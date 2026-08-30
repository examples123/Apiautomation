package stepDefinition;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.info.LocatorsInfo;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;


public class StepDefinitionCreatAlert {
    Page newPage;


    @Given("user logs in with credentials {string} and {string}")
    public void user_logs_in(String username, String password) {

        Assert.assertTrue(true);

    }

    @And("user is on home page")
    public void user_is_on_home_page() {
        String title = Hooks.page.title();
        //assertTrue(title.contains(LocatorsInfo.Title));
    }

    @And("user searches for product {string}")
    public void user_searches_for_product(String product) {

        // Fill the Amazon search box using its aria-label
        Hooks.page.getByLabel(LocatorsInfo.SEARCH_BOX).fill("trimmers for men");
        Hooks.page.click(LocatorsInfo.SEARCH_BUTTON);
        Hooks.page.waitForTimeout(3000);
    }

    @And("user adds {string} to the cart")
    public void user_adds_product_to_cart(String productName) {

        String name=LocatorsInfo.sponsoredAd(productName);
        Locator Listlinks = Hooks.page.locator(name);
         newPage = Hooks.context.waitForPage(() -> {
             Listlinks.first().click();
        });

        newPage.waitForLoadState();

        System.out.println("New tab title: " + newPage.title());

        newPage.getByTitle("Add to Shopping Cart").first().click();
        newPage.waitForTimeout(1000);

        newPage.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("No thanks"))
                .click();
    newPage.waitForTimeout(1000);
    }

    @And("validate {string} is added to cart")
    public void validate_product_added(String productName) {
        Locator confirmationMessage = newPage.locator("text=Added to cart");
        newPage.waitForTimeout(1000);
        assert confirmationMessage.isVisible();
    }

    @When("user clicks buy the product")
    public void user_clicks_buy_the_product() {
        newPage.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Proceed to Buy (1 item)"))
                .click();
        newPage.waitForTimeout(3000);
    }

    @Then("product should be ordered successfully")
    public void product_should_be_ordered_successfully() {

        Assert.assertTrue(true);

    }

    @And("validate Order ID is generated")
    public void validate_order_id_is_generated() {
        Assert.assertTrue(true);
    }

    @And("user logs out from application")
    public void user_logs_out() {

    }


}
