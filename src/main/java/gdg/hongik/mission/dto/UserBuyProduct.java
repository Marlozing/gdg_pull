package gdg.hongik.mission.dto;

import lombok.Getter;

@Getter
public class UserBuyProduct {
    private String productName;
    private int productAmount;
    private int productPrice;

    public UserBuyProduct(String productName, int productAmount, int productPrice){
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
    }
}
