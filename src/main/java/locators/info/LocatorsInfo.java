package locators.info;

public class LocatorsInfo {

    public static final String SEARCH_BOX = "Search Amazon.in";
    public static final String SEARCH_BUTTON = "#nav-search-submit-button";
    public static final String ADD_TO_CART_TITLE = "Add to Shopping Cart";
    public static final String NO_THANKS_BUTTON = "No thanks";
    public static final String PROCEED_TO_BUY = "Proceed to Buy (1 item)";
    public static final String Title = "Proceed to Buy (1 item)";

    public static String sponsoredAd(String productName) {
        return "//*[contains(@aria-label,'Sponsored Ad - " + productName + "')]/..";
    }

    public static String confirmationText() {
        return "text=Added to cart";
    }
}
