package gdg.hongik.mission.controller;

import gdg.hongik.mission.entity.Product;

import gdg.hongik.mission.service.ProductAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="관리자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("product-admins")
public class ProductAdminController {
    private final ProductAdminService productAdminService;

    @Operation(summary = "상품 생성", description = "새 상품을 등록한다")
    @ApiResponse(responseCode = "200", description = "생성 성공")
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        productAdminService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 재고 추가", description = "상품 재고를 추가한다")
    @ApiResponse(responseCode = "200", description = "추가 성공")
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> patchProductStock(
            @PathVariable Long productId,
            @RequestBody Integer productStock){
        Product product = productAdminService.updateStock(productId, productStock);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "상품 제거", description = "상품을 제거한다")
    @ApiResponse(responseCode = "200", description = "제거 성공")
    @DeleteMapping
    public ResponseEntity<List<Product>> deleteProduct(@RequestBody List<Long> productIds){
        List<Product> products = productAdminService.deleteProduct(productIds);
        return ResponseEntity.ok(products);
    }
}
