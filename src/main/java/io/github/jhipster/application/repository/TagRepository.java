package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tag;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

}
