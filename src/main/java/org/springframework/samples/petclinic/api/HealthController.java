package org.springframework.samples.petclinic.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	/**
	 * Global Load Balancer health check endpoint.
	 *
	 * ⚠️ IMPORTANT:
	 * - Do NOT call database
	 * - Do NOT call Redis
	 * - Do NOT call internal services
	 * - Must return 200 fast (<1ms)
	 */
	@GetMapping("/healthz")
	public String healthz() {
		return "ok";
	}
}
