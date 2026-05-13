package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.AdminCreateRequest;
import gdg.hongik.mission.dto.AdminDeleteProduct;
import gdg.hongik.mission.dto.AdminPatchResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    public void createProduct(AdminCreateRequest request){
        Product existingProduct = productRepository.findByName(request.getProductName());

        if (existingProduct != null){
            throw new RuntimeException("이미 존재하는 상품입니다: " + request.getProductName());
        }
        Product product = new Product(request.getProductName(), request.getProductPrice(), request.getProductStock());
        productRepository.save(product);
    }

    public AdminPatchResponse updateStock(Long productId, Integer productStock){
        Product product = productRepository.findById(productId);

        if (product == null){
            throw new RuntimeException("상품을 찾을 수 없습니다: " + productId);
        }

        product.setProductStock(product.getProductStock() + productStock);
        return new AdminPatchResponse(product.getProductName(), product.getProductPrice(), product.getProductStock());

    }

    public List<AdminDeleteProduct> deleteProduct(List<Long> productIds){
        for (Long id : productIds){
            Product product = productRepository.findById(id);

            if (product == null){
                throw new RuntimeException("상품을 찾을 수 없습니다: " + id);
            }
            productRepository.deleteById(id);
        }
        List<AdminDeleteProduct> response = new ArrayList<>();
        for (Product product : productRepository.findAll()){
            response.add(new AdminDeleteProduct(product.getProductName(), product.getProductStock()));
        }
        return response;
    }
}
