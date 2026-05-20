package gdg.hongik.mission.common.message;

public class ErrorMessage {

    public static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다.";
    public static final String PRODUCT_ALREADY_EXISTS = "이미 존재하는 상품입니다.";
    public static final String PRODUCT_STOCK_SHORTAGE = "상품의 재고가 부족합니다";

    // DTO
    public static final String PRODUCT_ID_NOT_NULL = "상품 ID는 필수입니다.";

    public static final String PRODUCT_NAME_NOT_NULL = "상품 이름은 필수입니다.";

    public static final String PRODUCT_PRICE_NOT_NULL = "상품 가격은 필수입니다.";
    public static final String PRODUCT_PRICE_INVALID = "상품 가격은 0원 이상이어야합니다.";

    public static final String PRODUCT_STOCK_NOT_NULL = "상품 재고는 필수입니다.";
    public static final String PRODUCT_STOCk_INVALID = "상품 재고는 0개 이상이어야합니다.";

    public static final String PRODUCT_AMOUNT_NOT_NULL = "상품 구매 갯수는 필수입니다.";
    public static final String PRODUCT_AMOUNT_INVALID = "상품 구매 최소 갯수는 1개 이상이어야합니다.";



}
