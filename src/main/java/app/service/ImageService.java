package app.service;

import app.model.Image;
import app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(Image newImage) {
        imageRepository.save(newImage);
    }

    public Image getImageByIdEnchere(int idEnchere) {
        return imageRepository.findImageByIdEnchere(idEnchere);
    }
}
