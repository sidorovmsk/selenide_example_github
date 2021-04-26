package github.elements.main;

import github.elements.ElementPrototype;
import github.elements.simple.HeaderMenuDropDown;
import github.elements.simple.Link;

public class HeaderMenu extends ElementPrototype {
    private final String HEADER_MENU_XPATH = "//div[contains(@class,'HeaderMenu')]";
    public HeaderMenuDropDown whyDropDown = new HeaderMenuDropDown("pricingDropDown", HEADER_MENU_XPATH + "//summary[contains(text(),'Why GitHub?')]");
    public Link teamLink = new Link("teamLink", HEADER_MENU_XPATH + "//*[contains(text(),'Team')]");
    public Link enterpriseLink = new Link("enterpriseLink", HEADER_MENU_XPATH + "//*[contains(text(),'Enterprise')]");
    public HeaderMenuDropDown exploreDropDown = new HeaderMenuDropDown("pricingDropDown", HEADER_MENU_XPATH + "//summary[contains(text(),'Explore')]");
    public Link marketplaceLink = new Link("marketplaceLink", HEADER_MENU_XPATH + "//*[contains(text(),'Marketplace')]");
    public HeaderMenuDropDown pricingDropDown = new HeaderMenuDropDown("pricingDropDown", HEADER_MENU_XPATH + "//summary[contains(text(),'Pricing')]");
    public Link signInLink = new Link("signInLink", HEADER_MENU_XPATH + "//*[contains(text(),'Sign in')]");
    public Link signUpLink = new Link("signUpLink", HEADER_MENU_XPATH + "//*[contains(text(),'Sign up')]");
}
