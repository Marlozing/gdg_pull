package gdg.hongik.mission.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserBuyResponse {

    private int totalPrice;
    private List<UserBuyProduct> products;

    public UserBuyResponse(int totalPrice, List<UserBuyProduct> products){
        this.totalPrice = totalPrice;
        this.products = products;
    }
}
