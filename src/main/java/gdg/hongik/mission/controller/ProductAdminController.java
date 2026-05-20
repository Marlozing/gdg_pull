package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.AdminCreateRequest;
import gdg.hongik.mission.dto.AdminDeleteProduct;
import gdg.hongik.mission.dto.AdminPatchResponse;

import gdg.hongik.mission.service.ProductAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@Tag(name="관리자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/products")
public class ProductAdminController {
    private final ProductAdminService productAdminService;



    @Operation(summary = "상품 생성", description = "새 상품을 등록한다")
    @ApiResponse(responseCode = "200", description = "생성 성공")
    @PostMapping("/add")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid AdminCreateRequest request){
        productAdminService.createProduct(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 재고 추가", description = "상품 재고를 추가한다")
    @ApiResponse(responseCode = "200", description = "추가 성공")
    @PatchMapping("/{productName}")
    public ResponseEntity<AdminPatchResponse> patchProductStock(
            @PathVariable String productName,
            @RequestBody Integer productStock){
        AdminPatchResponse response = productAdminService.updateStock(productName, productStock);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "상품 제거", description = "상품을 제거한다")
    @ApiResponse(responseCode = "200", description = "제거 성공")
    @DeleteMapping("/delete")
    public ResponseEntity<List<AdminDeleteProduct>> deleteProduct(@RequestBody List<String> productNames){
        List<AdminDeleteProduct> products = productAdminService.deleteProduct(productNames);
        return ResponseEntity.ok(products);
    }
}
