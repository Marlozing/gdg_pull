package gdg.hongik.mission.dto;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserBuyRequest {

    @NotNull(message = ErrorMessage.PRODUCT_ID_NOT_NULL)
    private Long ProductId;

    @NotNull(message = ErrorMessage.PRODUCT_AMOUNT_NOT_NULL)
    @Min(value = 1, message = ErrorMessage.PRODUCT_AMOUNT_INVALID)
    private int ProductAmount;

    public UserBuyRequest(Long ProductId, int ProductAmount){
        this.ProductId = ProductId;
        this.ProductAmount = ProductAmount;
    }
}
