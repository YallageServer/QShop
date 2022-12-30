package yallage.qshop.shops;

public class Cargo {
    private long id;
    private String displayName;
    private boolean unlimited;
    private String material;
    private String initStack;
    private String minStack;
    private String maxStack;
    private String minPrice;
    private String maxPrice;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String value) { this.displayName = value; }

    public boolean getUnlimited() { return unlimited; }
    public void setUnlimited(boolean value) { this.unlimited = value; }

    public String getMaterial() { return material; }
    public void setMaterial(String value) { this.material = value; }

    public String getInitStack() { return initStack; }
    public void setInitStack(String value) { this.initStack = value; }

    public String getMinStack() { return minStack; }
    public void setMinStack(String value) { this.minStack = value; }

    public String getMaxStack() { return maxStack; }
    public void setMaxStack(String value) { this.maxStack = value; }

    public String getMinPrice() { return minPrice; }
    public void setMinPrice(String value) { this.minPrice = value; }

    public String getMaxPrice() { return maxPrice; }
    public void setMaxPrice(String value) { this.maxPrice = value; }
}
