package org.springframework.samples.petclinic.debug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DebugController {

	@GetMapping("/debug/info")
	public Map<String, Object> info() throws Exception {

		Map<String, Object> result = new HashMap<>();

		String region = System.getenv("REGION");

		String instance = System.getenv("K_REVISION") + " / " + System.getenv("K_SERVICE");

		String hostname = InetAddress.getLocalHost().getHostName();

		String dbHost = System.getenv("DB_HOST");
		String dbName = System.getenv("DB_NAME");

		result.put("region", region);
		result.put("instance", instance);
		result.put("hostname", hostname);
		result.put("db_host", dbHost);
		result.put("db_name", dbName);
		result.put("status", "OK");

		return result;
	}

}
