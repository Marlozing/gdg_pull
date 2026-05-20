package gdg.hongik.mission.repository;

import gdg.hongik.mission.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaProductRepository implements ProductRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product findById(Long id){
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll(){
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product findByName(String name){
        List<Product> result = em.createQuery(
                "SELECT p FROM Product p WHERE p.ProductName = :name", Product.class
        ).setParameter("name", name).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void save(Product product){
        em.persist(product);
    }

    @Override
    public void deleteById(Long id){
        Product product = em.find(Product.class, id);
        em.remove(product);
    }
}
