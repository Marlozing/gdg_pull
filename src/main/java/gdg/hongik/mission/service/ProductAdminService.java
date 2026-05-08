package gdg.hongik.mission.service;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    public void createProduct(Product product){
        Product existingProduct = productRepository.findByName(product.getProductName());

        if (existingProduct != null){
            throw new RuntimeException("이미 존재하는 상품입니다: " + product.getProductName());
        }
        productRepository.save(product);
    }

    public Product updateStock(Long productId, Integer productStock){
        Product product = productRepository.findById(productId);

        if (product == null){
            throw new RuntimeException("상품을 찾을 수 없습니다: " + productId);
        }

        product.setProductStock(product.getProductStock() + productStock);
        return product;

    }

    public List<Product> deleteProduct(List<Long> productIds){
        for (Long id : productIds){
            Product product = productRepository.findById(id);

            if (product == null){
                throw new RuntimeException("상품을 찾을 수 없습니다: " + id);
            }
            productRepository.deleteById(id);
        }
        List<Product> leftProducts = productRepository.findAll();
        return leftProducts;
    }
}
