package gdg.hongik.mission.service;

import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import gdg.hongik.mission.dto.AdminCreateRequest;
import gdg.hongik.mission.dto.AdminDeleteProduct;
import gdg.hongik.mission.dto.AdminPatchResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
@Transactional
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService{

    private final ProductRepository productRepository;

    @Override
    public void createProduct(AdminCreateRequest request){
        Product existingProduct = productRepository.findByName(request.getProductName());

        if (existingProduct != null){
            throw new BadRequestException(ErrorMessage.PRODUCT_ALREADY_EXISTS);
        }
        Product product = new Product(request.getProductName(), request.getProductPrice(), request.getProductStock());
        productRepository.save(product);
    }

    @Override
    public AdminPatchResponse updateStock(String productName, Integer productStock){
        Product product = productRepository.findByName(productName);

        if (product == null){
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        product.setProductStock(product.getProductStock() + productStock);
        return new AdminPatchResponse(product.getProductName(), product.getProductPrice(), product.getProductStock());

    }

    @Override
    public List<AdminDeleteProduct> deleteProduct(List<String> productNames){
        for (String name : productNames){
            Product product = productRepository.findByName(name);

            if (product == null){
                throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
            }
            productRepository.deleteById(product.getProductId());
        }
        List<AdminDeleteProduct> response = new ArrayList<>();
        for (Product product : productRepository.findAll()){
            response.add(new AdminDeleteProduct(product.getProductName(), product.getProductStock()));
        }
        return response;
    }
}
