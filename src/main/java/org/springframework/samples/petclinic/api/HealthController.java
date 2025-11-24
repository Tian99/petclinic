package org.springframework.samples.petclinic.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

	@GetMapping("/healthz")
	public ResponseEntity<Map<String, Object>> health() {
		if ("true".equals(System.getenv("HEALTH_FAIL"))) {
			return ResponseEntity.status(500).body(Map.of("status", "fail"));
		}
		return ResponseEntity.ok(Map.of("status", "ok"));
	}
}
