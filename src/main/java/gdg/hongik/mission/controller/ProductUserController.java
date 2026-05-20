package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.UserBuyRequest;
import gdg.hongik.mission.dto.UserBuyResponse;
import gdg.hongik.mission.dto.UserBuyProduct;
import gdg.hongik.mission.entity.Product;


import gdg.hongik.mission.service.ProductUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins="*")
@Tag(name="유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/products")
public class ProductUserController {
    private final ProductUserService productUserService;

    @Operation(summary = "상품 검색", description = "상품을 검색한다")
    @ApiResponse(responseCode = "200", description = "검색 성공")
    @GetMapping("/find")
    public ResponseEntity<Product> getProduct(@PathVariable String productName){
        Product product = productUserService.findbyName(productName);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "상품 구매", description = "상품들을 구매한다")
    @ApiResponse(responseCode = "200", description = "구매 성공")
    @PatchMapping("/buy")
    public ResponseEntity<UserBuyResponse> buyProduct(
            @RequestBody @Valid List<UserBuyRequest> products){
        UserBuyResponse response = productUserService.buyProducts(products);
        return ResponseEntity.ok(response);
    }
}
