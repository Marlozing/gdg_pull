package gdg.hongik.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductId;

    private String ProductName;

    private Integer ProductPrice;

    private Integer ProductStock;

    public Product(String ProductName, Integer ProductPrice, Integer ProductStock) {
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }

    public void setProductStock(Integer ProductStock){
        if (ProductStock != null){
            this.ProductStock = ProductStock;
        }
    }
    /*
    public void updateInfo(String ProductName, Integer ProductPrice, Integer ProductStock){
        if (ProductName != null){
            this.ProductName = ProductName;
        }

        if (ProductPrice != null){
            this.ProductPrice = ProductPrice;
        }

        if (ProductStock != null){
            this.ProductStock = ProductStock;
        }
    }
    */
}
