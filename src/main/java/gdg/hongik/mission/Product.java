package gdg.hongik.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long ProductId;
    private String ProductName;
    private Integer ProductPrice;
    private Integer ProductStock;

    public Product(Long ProductId, String ProductName, Integer ProductPrice, Integer ProductStock) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }
}
