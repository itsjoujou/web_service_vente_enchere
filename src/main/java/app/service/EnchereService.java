package app.service;

import app.model.*;
import app.repository.*;
import app.util.Data;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnchereService {
    @Autowired
    private final EnchereRepository enchereRepository;

    @Autowired
    private final EtatEnchereRepository etatEnchereRepository;

    @Autowired
    private final CommissionRepository commissionRepository;

    @Autowired
    private final DureeEnchereRepository dureeEnchereRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EnchereService(EnchereRepository enchereRepository,
                          EtatEnchereRepository etatEnchereRepository,
                          CommissionRepository commissionRepository,
                          DureeEnchereRepository dureeEnchereRepository,
                          ImageRepository imageRepository) {
        this.enchereRepository = enchereRepository;
        this.etatEnchereRepository = etatEnchereRepository;
        this.commissionRepository = commissionRepository;
        this.dureeEnchereRepository = dureeEnchereRepository;
        this.imageRepository = imageRepository;
    }

    public Object getEncheresInProgress() {
        List<EtatEnchere> enchereList = etatEnchereRepository.findEtatEncheresByDateFinAfter(Timestamp.valueOf(LocalDateTime.now()));
        this.setImageToEtatEnchereList(enchereList);

        return new Data(enchereList);
    }

    public Object getEnchere(int id_enchere) {
        Optional<EtatEnchere> optionalEnchere = etatEnchereRepository.findById(id_enchere);
        this.setImageToEtatEnchere(optionalEnchere.get());

        return new Data(optionalEnchere.get());
    }

    public Object getEncheresByIdUser(int id_user) {
        List<EtatEnchere> enchereList =  etatEnchereRepository.findByIdUser(id_user);
        this.setImageToEtatEnchereList(enchereList);

        return new Data(enchereList);
    }

    public void save(EnchereImage enchereImage) throws Exception {
        Commission commission = commissionRepository.findCurrentCommission().get();
        DureeEnchere dureeEnchere = dureeEnchereRepository.findFirstByDateDebutBeforeOrderByDateDebutDesc(Timestamp.valueOf(LocalDateTime.now())).get();
        Enchere newEnchere = enchereImage.getEnchere();
        Image image = enchereImage.getImages();

        newEnchere.setDateDebut(Timestamp.valueOf(LocalDateTime.now()));
        if (newEnchere.getDuree() <= dureeEnchere.getDureeMin()) {
            throw new Exception("La durée minimum autorisée est de "+ dureeEnchere.getDureeMin() +"h");
        }
        else if (newEnchere.getDuree() >= dureeEnchere.getDureeMax()) {
            throw new Exception("La durée maximum autorisée est de "+ dureeEnchere.getDureeMax() +"h");
        }
        newEnchere.setDateFin(Timestamp.valueOf(LocalDateTime.now().plusHours(newEnchere.getDuree())));
        newEnchere.setCommission(commission.getPourcentage());

        newEnchere = enchereRepository.save(newEnchere);
        image.setIdEnchere(newEnchere.getId());
        image.setBase64(enchereImage.getImages().getBase64());
        imageRepository.save(image);
    }

    public Object findEnchereWithRechercheAvancee(EnchereRechercheCriteria filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Enchere> query = builder.createQuery(Enchere.class);
        Root<Enchere> enchereRoot = query.from(Enchere.class);
        Predicate criteria = builder.conjunction();

        if (filter.getKeyWord() != null || !filter.getKeyWord().isEmpty()) {
            Predicate keyWordLike = builder.like(builder.upper(enchereRoot.get("caption")), "%"+ filter.getKeyWord().toUpperCase() +"%");
            criteria = builder.and(criteria, keyWordLike);
        }
        if (filter.getIdCategorie() > 0) {
            Predicate IsInCategorie = builder.equal(enchereRoot.get("categorie").get("id"), filter.getIdCategorie());
            criteria = builder.and(criteria, IsInCategorie);
        }
        if (filter.getPrixMax() != 0) {
            Predicate lessThanPrixMax = builder.lessThanOrEqualTo(enchereRoot.get("montantDebutEnchere"), filter.getPrixMax());
            criteria = builder.and(criteria, lessThanPrixMax);
        }
        if (filter.getPrixMin() != 0) {
            Predicate greaterThanPrixMin = builder.greaterThanOrEqualTo(enchereRoot.get("montantDebutEnchere"), filter.getPrixMin());
            criteria = builder.and(criteria, greaterThanPrixMin);
        }
        if (filter.getStatut()) {
            Predicate hasEnded = builder.lessThanOrEqualTo(enchereRoot.get("dateDebut"), filter.getDate());
            criteria = builder.and(criteria, hasEnded);
        }

        query.select(enchereRoot).where(criteria);
        List<Enchere> enchereList = entityManager.createQuery(query).getResultList();
        this.setImageToEnchereList(enchereList);

        return new Data(enchereList);
    }

    public void setImageToEnchereList(List<Enchere> enchereList) {
        for (Enchere enchere : enchereList) {
            this.setImageToEnchere(enchere);
        }
    }

    public void setImageToEnchere(Enchere newEnchere) {
        newEnchere.setImage(imageRepository.findImageByIdEnchere(newEnchere.getId()));
    }

    public void setImageToEtatEnchereList(List<EtatEnchere> etatEnchereList) {
        for (EtatEnchere enchere : etatEnchereList) {
            this.setImageToEtatEnchere(enchere);
        }
    }

    public void setImageToEtatEnchere(EtatEnchere newEnchere) {
        newEnchere.setImage(imageRepository.findImageByIdEnchere(newEnchere.getIdEnchere()));
    }
}
