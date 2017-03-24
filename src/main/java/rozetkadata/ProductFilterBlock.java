package rozetkadata;

import java.util.ArrayList;
import java.util.List;

public class ProductFilterBlock {
    private String Title;
    private List<ProductFilterBlockInstance> filterItems=new ArrayList<ProductFilterBlockInstance>();

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<ProductFilterBlockInstance> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<ProductFilterBlockInstance> filterItems) {
        this.filterItems = filterItems;
    }
}
