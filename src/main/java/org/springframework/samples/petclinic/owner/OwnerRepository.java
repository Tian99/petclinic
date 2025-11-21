package org.springframework.samples.petclinic.owner;

import java.util.Optional;
import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.samples.petclinic.api.OwnerSummaryDto;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	/**
	 * Retrieve {@link Owner}s from the data store by last name, returning all owners
	 * whose last name <i>starts</i> with the given name.
	 */
	Page<Owner> findByLastNameStartingWith(String lastName, Pageable pageable);

	/**
	 * Retrieve an {@link Owner} from the data store by id.
	 */
	Optional<Owner> findById(Integer id);

	@Query("""
        select new org.springframework.samples.petclinic.api.OwnerSummaryDto(
            o.id, o.firstName, o.lastName, o.city
        )
        from Owner o
        """)
	List<OwnerSummaryDto> findAllSummaries();
}
