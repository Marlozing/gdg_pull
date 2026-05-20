package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.UserBuyRequest;
import gdg.hongik.mission.dto.UserBuyResponse;
import gdg.hongik.mission.entity.Product;

import java.util.List;

public interface ProductUserService {
    Product findbyName(String productName);
    UserBuyResponse buyProducts(List<UserBuyRequest> products);

}
