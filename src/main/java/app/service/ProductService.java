package app.service;

import app.model.Product;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public List<Product> list() {
        return new ArrayList<>(products.values());
    }

    public Product get(Long id) {
        return products.get(id);
    }

    public Product create(Product p) {
        p.setId(nextId++);
        products.put(p.getId(), p);
        return p;
    }

    public boolean delete(Long id) {
        return products.remove(id) != null;
    }
}
