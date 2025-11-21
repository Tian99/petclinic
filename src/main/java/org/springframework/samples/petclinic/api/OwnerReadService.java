package org.springframework.samples.petclinic.api;

import java.util.List;

import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

@Service
public class OwnerReadService {

	private final OwnerRepository ownerRepository;

	public OwnerReadService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional(readOnly = true)
	@Cacheable("ownerSummaries")
	public List<OwnerSummaryDto> getAllSummaries() {
		return ownerRepository.findAllSummaries();
	}

}
