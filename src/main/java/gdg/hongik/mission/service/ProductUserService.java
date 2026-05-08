package gdg.hongik.mission.service;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUserService {

    private final ProductRepository productRepository;

    public Product findbyName(String productName){
        Product product = productRepository.findByName(productName);

        if (product == null){
            throw new RuntimeException("상품을 찾을 수 없습니다: " + productName);
        }
        return product;
    }

    public List<Product> buyProducts(List<Product> products) {
        List<Product> buyProducts = new ArrayList<>();
        for (Product product : products) {
            Product existingProduct = productRepository.findById(product.getProductId());

            if (existingProduct == null) {
                throw new RuntimeException("상품을 찾을 수 없습니다: " + product.getProductId());
            }

            if (existingProduct.getProductStock() < product.getProductStock()) {
                throw new RuntimeException("상품의 재고가 부족합니다: " + product.getProductId());
            }

            existingProduct.setProductStock(existingProduct.getProductStock() - product.getProductStock());
            buyProducts.add(existingProduct);
        }
        return buyProducts;
    }
}
