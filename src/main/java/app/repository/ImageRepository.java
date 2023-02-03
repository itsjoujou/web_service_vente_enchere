package app.repository;

import app.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
public interface ImageRepository extends MongoRepository<Image, String> {
    @Transactional
    Image findImageByIdEnchere(int idEnchere);
}
