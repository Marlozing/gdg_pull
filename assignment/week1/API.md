## 소비자 (User)


## 1. 상품 조회

### HTTP Method : GET
### URL : /product-users
### Input JSON : "제품 이름"
### Output JSON : {
    "productId": 1,
    "productName": "제품 이름",
    "productPrice": 1000,
    "productStock": 5
} 

## 2. 상품 구매

### HTTP Method : PATCH
### URL : /product-users
### Input JSON : [
        {
            "productId": 1,
            "productAmount": 6
        },
        {
            "productId": 2,
            "productAmount": 7
        }
]
### Output JSON : {
    "totalPrice": 10000,
    "products": [
        {
            "productName": "제품 이름",
            "productAmount": 6,
            "productPrice": 1000
        },
        {
            "productName": "제품 이름",
            "productAmount": 7,
            "productPrice": 2000
        }
    ]
}

## 관리자 (Admin)


## 1. 상품 등록

### HTTP Method : POST
### URL : /product-admins
### Input JSON : {
    "productName": "제품 이름",
    "productPrice": 1000,
    "productStock": 5
}

## 2. 재고 추가

### HTTP Method : PATCH
### URL : /product-admins/{productId}
### Input JSON : 5
### Output JSON : {
    "productName": "제품 이름",
    "productPrice": 1000,
    "productStock": 10
}

## 3. 상품 삭제

### HTTP Method : DELETE
### URL : /product-admins
### Input JSON : [
        {
            "productId": 1
        },
        {
            "productId": 2
        }
]
### OutPut JSON : {
    "products": [
        {
            "productName": "남은 제품 이름",
            "productStock": 5
        },
        {
            "productName": "남은 제품 이름",
            "productStock": 10
        }
    ]
}