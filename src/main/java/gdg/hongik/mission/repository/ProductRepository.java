package gdg.hongik.mission.repository;


import gdg.hongik.mission.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    Product findByName(String name);
    void save(Product product);
    void deleteById(Long id);
}
