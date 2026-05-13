package gdg.hongik.mission.dto;

import lombok.Getter;

@Getter
public class UserBuyRequest {
    private Long ProductId;
    private int ProductAmount;

    public UserBuyResponse(Long ProductId, int ProductAmount){
        this.ProductId = ProductId;
        this.ProductAmount = ProductAmount;
    }
}
