package it.igesa.strategy.impl;

import it.igesa.domaine.Post;
import it.igesa.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class PostServiceImpl implements Strategy<Post> {
    @Override
    public Post savePhoto(InputStream image, String titre, Long id) {
        return null;
    }
}
