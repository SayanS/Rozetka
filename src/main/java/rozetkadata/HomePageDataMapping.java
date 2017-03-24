package rozetkadata;

import java.util.List;

public class HomePageDataMapping {
    private String leftSideMenuCategoryTitle;
    private String rightSideMenuPromotionTitle;
    private List<String> leftSideMenuCategoryItems;

    public String getLeftSideMenuCategoryTitle() {
        return leftSideMenuCategoryTitle;
    }

    public String getRightSideMenuPromotionTitle() {
        return rightSideMenuPromotionTitle;
    }

    public List<String> getleftSideMenuCategoryItems() {
        return leftSideMenuCategoryItems;
    }

    public void setLeftSideMenuCategoryTitle(String leftSideMenuCategoryTitle) {
        this.leftSideMenuCategoryTitle = leftSideMenuCategoryTitle;
    }

    public void setRightSideMenuPromotionTitle(String rightSideMenuPromotionTitle) {
        this.rightSideMenuPromotionTitle = rightSideMenuPromotionTitle;
    }

    public void setLeftSideMenuCategoryItems(List<String> leftSideMenuCategoryItems) {
        this.leftSideMenuCategoryItems = leftSideMenuCategoryItems;
    }

}
