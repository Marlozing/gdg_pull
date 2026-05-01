package gdg.hongik.mission.controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name="유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/product-users")
public class ProductUserController {
    private final ProductStore productStore;

    @Operation(summary = "상품 검색", description = "상품을 검색한다")
    @ApiResponse(responseCode = "200", description = "검색 성공")
    @GetMapping
    public ResponseEntity<Product> getProduct(@RequestBody String productName){
        for (Product product : productStore.products){
            if (product.getProductName().equals(productName)){
                return ResponseEntity.ok(product);
            }
        }
        throw new RuntimeException("상품을 찾을 수 없습니다: " + productName);
    }

    @Operation(summary = "상품 구매", description = "상품들을 구매한다")
    @ApiResponse(responseCode = "200", description = "구매 성공")
    @PatchMapping
    public ResponseEntity<Map<String, Object>> buyProduct(
            @RequestBody List<Map<String, Object>> requests){
        Map<String, Object> response = new HashMap<>();
        int totalPrice = 0;
        List<Map<String, Object>> items = new ArrayList<>();
        for (Map<String ,Object> request : requests){
            boolean check = Boolean.FALSE;
            Long productId = (Long) request.get("productId");
            Integer productStock = (Integer) request.get("productAmount");

            for (Product product : productStore.products){
                if (product.getProductId().equals(productId)){
                    if (product.getProductStock() < productStock){
                        throw new RuntimeException("상품의 재고가 부족합니다: " + product.getProductId());
                    }
                    check = Boolean.TRUE;
                    product.setProductStock(product.getProductStock() - productStock);
                    Map<String, Object> item = new HashMap<>();
                    item.put("productName", product.getProductName());
                    item.put("productAmount", productStock);
                    item.put("productPrice", product.getProductPrice());
                    items.add(item);
                    totalPrice += productStock * product.getProductPrice();
                    break;
                }
            }
            if (check) {
                continue;
            }
            throw new RuntimeException("상품을 찾을 수 없습니다: " + productId);
        }
        response.put("totalPrice", totalPrice);
        response.put("items", items);
        return ResponseEntity.ok(response);
    }
}
