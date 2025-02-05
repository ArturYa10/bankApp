package de.telran.bankapp.service;

import de.telran.bankapp.entity.Product;
import de.telran.bankapp.entity.enums.CurrencyCode;
import de.telran.bankapp.entity.enums.ProductStatus;
import de.telran.bankapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getProductById(String id) {
        return repo.getProductById(id);
    }

    public Product addProduct(Product product) {
        return repo.addProduct(product);
    }

    public Optional<Product> updatedProduct(Product product) {
        String id = product.getId();
        Optional<Product> optional = repo.findById(id);
        if (optional.isPresent()) {
            Product found = optional.get();
            found.setName(product.getName());
            found.setStatus(product.getStatus());
            found.setCurrencyCode(product.getCurrencyCode());
            found.setInterestRate(product.getInterestRate());
            found.setLimitAmount(product.getLimitAmount());

            return Optional.of(found);
        } else
            return Optional.empty();
    }

    public ResponseEntity<Product> deleteProduct(String id) {
        boolean deleted = repo.deleteProduct(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    public List<Product> searchProductByCurrencyAndStatus(CurrencyCode currencyCode, ProductStatus status) {
        return repo.searchProductByCurrencyAndStatus(currencyCode, status);
    }

    public ResponseEntity<Void> deleteInactiveProducts() {
        return repo.deleteInactiveProducts();
    }

    public ResponseEntity<Product> changeStatus(String id, String status) {
        Optional<Product> optional = repo.findById(id);
        if (optional.isPresent()) {
            return repo.changeStatus(id, status);
        } else return ResponseEntity.notFound().build();
    }
}
