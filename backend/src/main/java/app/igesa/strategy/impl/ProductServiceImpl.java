package app.igesa.strategy.impl;

import app.igesa.entity.Product;
import app.igesa.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class ProductServiceImpl implements Strategy<Product> {
    @Override
    public Product savePhoto(InputStream image, String titre, Long id) {
        return null;
    }
}
