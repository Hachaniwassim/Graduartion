package app.igesa.strategy.impl;


import app.igesa.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickerServiceImp implements Strategy{
    @Override
    public Object savePhoto(InputStream image, String titre, Long id) {
        return null;
    }
}
