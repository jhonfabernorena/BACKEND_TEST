package test;

import app.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testApplyTaxOk() {
        Product p = new Product(1L, "Laptop", 1000.0);
        p.applyTax(10);
        assertEquals(1100.0, p.getPrice(), 0.001);
    }

    @Test
    public void testApplyInvalidTax() {
        Product p = new Product(1L, "Laptop", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> p.applyTax(60));
    }
}
