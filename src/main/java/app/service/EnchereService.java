package app.service;

import app.model.Commission;
import app.model.Enchere;
import app.model.EnchereRechercheCriteria;
import app.repository.CommissionRepository;
import app.repository.EnchereRepository;
import app.util.CustomError;
import app.util.Data;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnchereService {
    @Autowired
    private final EnchereRepository enchereRepository;

    @Autowired
    private final CommissionRepository commissionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EnchereService(EnchereRepository enchereRepository, CommissionRepository commissionRepository) {
        this.enchereRepository = enchereRepository;
        this.commissionRepository = commissionRepository;
    }

    public Object getEncheresInProgress() {
        return new Data(enchereRepository.findEnchereByDateFinAfter(Timestamp.valueOf(LocalDateTime.now())));
    }

    public Object getEnchere(int id_enchere) {
        Optional<Enchere> optionalEnchere = enchereRepository.findById(id_enchere);

        return optionalEnchere.isPresent() ? new Data(optionalEnchere.get()) : new CustomError("ID not found!");
    }

    public Object getEncheresByIdUser(int id_user) {
        return enchereRepository.findEnchereByIdUser(id_user);
    }

    public void save(Enchere newEnchere) {
        Optional<Commission> optionalCommission = commissionRepository.findCurrentCommission();
        newEnchere.setDateDebut(Timestamp.valueOf(LocalDateTime.now()));
        newEnchere.setDateFin(Timestamp.valueOf(LocalDateTime.now().plusHours(newEnchere.getDuree())));
        newEnchere.setCommission(optionalCommission.get().getPourcentage());

        enchereRepository.save(newEnchere);
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
        return new Data(entityManager.createQuery(query).getResultList());
    }
}
