package gdg.hongik.mission.dto;

public class AdminPatchResponse {
    private String ProductName;
    private Integer ProductPrice;
    private Integer ProductStock;

    public AdminPatchResponse(String ProductName, Integer ProductPrice, Integer ProductStock) {
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductStock = ProductStock;
    }
}
