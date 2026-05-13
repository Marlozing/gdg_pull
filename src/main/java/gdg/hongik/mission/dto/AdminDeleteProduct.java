package gdg.hongik.mission.dto;

public class AdminDeleteProduct {
    private String ProductName;
    private Integer ProductStock;

    public AdminDeleteProduct(String ProductName, Integer ProductStock) {
        this.ProductName = ProductName;
        this.ProductStock = ProductStock;
    }
}
