package it.igesa.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface Iflicker {
    String savePhoto(InputStream image, String title) throws FlickrException;
}
