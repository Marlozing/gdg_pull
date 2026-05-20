package gdg.hongik.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductId;

    private String ProductName;

    private Integer ProductPrice;

    @Setter
    private Integer ProductStock;

    public Product(String ProductName, Integer ProductPrice, Integer ProductStock) {
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }

}
