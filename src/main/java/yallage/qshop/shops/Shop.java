package yallage.qshop.shops;

import java.util.List;

public class Shop {
    private String shopName;
    private List<Cargo> cargos;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String value) {
        this.shopName = value;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> value) {
        this.cargos = value;
    }
}
