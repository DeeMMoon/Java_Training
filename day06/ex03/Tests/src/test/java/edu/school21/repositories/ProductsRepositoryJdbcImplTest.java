package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {

    private ProductsRepository productsRepository;
    private EmbeddedDatabase embeddedDatabase;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "product1", 100),
            new Product(2L, "product2", 110),
            new Product(3L, "product3", 120),
            new Product(4L, "product4", 130),
            new Product(5L, "product5", 140),
            new Product(6L, "product6", 150)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L, "product3", 120);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(5L, "new product", 1000);
    final Product EXPECTED_SAVE_PRODUCT = new Product(7L, "new saved product", 1);

    @BeforeEach
    void init()
    {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(embeddedDatabase);
    }

    @Test
    void findAllTest()
    {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void finsByIdTest()
    {
        Assertions.assertEquals(productsRepository.findById(3L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(100L), Optional.empty());
    }

    @Test
    void updateTest()
    {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(5L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void saveTest()
    {
        productsRepository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(7L).get(), EXPECTED_SAVE_PRODUCT);
    }

    @Test
    void deleteTest()
    {
        productsRepository.delete(5L);
        Assertions.assertEquals(productsRepository.findById(5L), Optional.empty());
    }

    @AfterEach
    void close()
    {
        embeddedDatabase.shutdown();
    }

}
