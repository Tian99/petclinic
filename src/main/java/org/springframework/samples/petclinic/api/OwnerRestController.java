package org.springframework.samples.petclinic.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {

	private final OwnerReadService ownerReadService;

	public OwnerRestController(OwnerReadService ownerReadService) {
		this.ownerReadService = ownerReadService;
	}

	@GetMapping("/summary")
	public List<OwnerSummaryDto> findAllSummaries() {
		return ownerReadService.getAllSummaries();
	}
}
