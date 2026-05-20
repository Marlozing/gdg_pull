package gdg.hongik.mission.service;

import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import gdg.hongik.mission.dto.UserBuyProduct;
import gdg.hongik.mission.dto.UserBuyRequest;
import gdg.hongik.mission.dto.UserBuyResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Primary
@Transactional
@Service
@RequiredArgsConstructor
public class ProductUserServiceImpl implements ProductUserService{

    private final ProductRepository productRepository;

    @Override
    public Product findbyName(String productName){
        Product product = productRepository.findByName(productName);

        if (product == null){
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public UserBuyResponse buyProducts(List<UserBuyRequest> products) {
        List<UserBuyProduct> buyProducts = new ArrayList<>();
        int totalPrice = 0;
        for (UserBuyRequest product : products) {
            Product existingProduct = productRepository.findById(product.getProductId());

            if (existingProduct == null) {
                throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
            }

            if (existingProduct.getProductStock() < product.getProductAmount()) {
                throw new BadRequestException(ErrorMessage.PRODUCT_STOCK_SHORTAGE);
            }

            existingProduct.setProductStock(existingProduct.getProductStock() - product.getProductAmount());
            totalPrice += product.getProductAmount() * existingProduct.getProductPrice();
            UserBuyProduct productDto = new UserBuyProduct(
                    existingProduct.getProductName(),
                    product.getProductAmount(),
                    existingProduct.getProductPrice() * product.getProductAmount()
            );
            buyProducts.add(productDto);
        }
        return new UserBuyResponse(totalPrice, buyProducts);
    }
}
