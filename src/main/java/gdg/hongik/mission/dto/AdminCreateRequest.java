package gdg.hongik.mission.dto;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdminCreateRequest {

    @NotNull(message = ErrorMessage.PRODUCT_NAME_NOT_NULL)
    private String ProductName;

    @NotNull(message = ErrorMessage.PRODUCT_PRICE_NOT_NULL)
    @Min(value = 0, message = ErrorMessage.PRODUCT_PRICE_INVALID)
    private int ProductPrice;

    @NotNull(message = ErrorMessage.PRODUCT_STOCK_NOT_NULL)
    @Min(value = 0, message = ErrorMessage.PRODUCT_STOCk_INVALID)
    private int ProductStock;

    public AdminCreateRequest(String ProductName, int ProductPrice, int ProductStock) {
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }
}
