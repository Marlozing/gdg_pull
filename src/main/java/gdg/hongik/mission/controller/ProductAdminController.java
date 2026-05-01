package gdg.hongik.mission.controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="관리자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("product-admins")
public class ProductAdminController {
    private final ProductStore productStore;

    @Operation(summary = "상품 생성", description = "새 상품을 등록한다")
    @ApiResponse(responseCode = "200", description = "생성 성공")
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Map<String, Object> request){
        String productName = (String) request.get("productName");
        Integer productPrice = (Integer) request.get("productPrice");
        Integer productStock = (Integer) request.get("productStock");

        for(Product product : productStore.products){
            if (product.getProductName().equals(productName)){
                throw new RuntimeException("이미 존재하는 상품입니다: " + productName);
            }
        }

        Product product = new Product(
                productStore.sequence,
                productName,
                productPrice,
                productStock
        );
        productStore.sequence++;
        productStore.products.add(product);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 재고 추가", description = "상품 재고를 추가한다")
    @ApiResponse(responseCode = "200", description = "추가 성공")
    @PatchMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> patchProductStock(
            @PathVariable Long productId,
            @RequestBody Integer productStock){
        for (Product product : productStore.products) {
            if (product.getProductId().equals(productId)) {
                product.setProductStock(product.getProductStock() + productStock);
                Map<String, Object> response = new HashMap<>();
                response.put("productName", product.getProductName());
                response.put("productStock", product.getProductStock());
                return ResponseEntity.ok(response);
            }
        }
        throw new RuntimeException("상품을 찾을 수 없습니다: " + productId);
    }

    @Operation(summary = "상품 제거", description = "상품을 제거한다")
    @ApiResponse(responseCode = "200", description = "제거 성공")
    @DeleteMapping
    public ResponseEntity<List<Map<String, Object>>> deleteProduct(@RequestBody List<Long> productIds){
        List<Map<String, Object>> response = new ArrayList<>();
        for (Long productId : productIds) {
            boolean check = Boolean.FALSE;
            for (Product product : productStore.products){
                if (product.getProductId().equals(productId)){
                    productStore.products.remove(product);
                    check = Boolean.TRUE;
                    break;
                }
            }
            if (check){
                continue;
            }
            throw new RuntimeException("상품을 찾을 수 없습니다: " + productId);
        }
        for (Product product: productStore.products){
            Map<String, Object> item = new HashMap<>();
            item.put("productName", product.getProductName());
            item.put("productStock", product.getProductStock());
            response.add(item);
        }

        return ResponseEntity.ok(response);
    }
}
