package gdg.hongik.mission.dto;

import lombok.Getter;

@Getter
public class AdminCreateRequest {
    private String ProductName;
    private int ProductPrice;
    private int ProductStock;

    public AdminCreateRequest(String ProductName, int ProductPrice, int ProductStock) {
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }
}
