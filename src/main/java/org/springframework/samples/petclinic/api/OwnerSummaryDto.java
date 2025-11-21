package org.springframework.samples.petclinic.api;

public record OwnerSummaryDto(
	Integer id,
	String firstName,
	String lastName,
	String city
) {
}
