package ismomotors.byd.dynorblue.api.piece.repository;

import ismomotors.byd.dynorblue.api.piece.dto.PieceFilterDTO;
import ismomotors.byd.dynorblue.api.piece.entity.Piece;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PieceSpecification {

    public Specification<Piece> getSpecification(PieceFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filter.getNumberPart())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("numberPart")),
                        "%" + filter.getNumberPart().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(filter.getVin())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("vin")),
                        "%" + filter.getVin().toLowerCase() + "%"
                ));
            }

            if (filter.getStock() != null) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), filter.getStock()));
            }

            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            if (StringUtils.hasText(filter.getOperator())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("operator")),
                        "%" + filter.getOperator().toLowerCase() + "%"
                ));
            }

            if (filter.getDateEntryFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("dateEntry"), filter.getDateEntryFrom()
                ));
            }

            if (filter.getDateEntryTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("dateEntry"), filter.getDateEntryTo()
                ));
            }

            if (filter.getReportingDateFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("reportingDate"), filter.getReportingDateFrom()
                ));
            }

            if (filter.getReportingDateTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("reportingDate"), filter.getReportingDateTo()
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
