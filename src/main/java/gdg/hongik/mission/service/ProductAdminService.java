package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.AdminCreateRequest;
import gdg.hongik.mission.dto.AdminDeleteProduct;
import gdg.hongik.mission.dto.AdminPatchResponse;

import java.util.List;

public interface ProductAdminService {
    void createProduct(AdminCreateRequest request);
    AdminPatchResponse updateStock(String productName, Integer productStock);
    List<AdminDeleteProduct> deleteProduct(List<String> productNames);

}
