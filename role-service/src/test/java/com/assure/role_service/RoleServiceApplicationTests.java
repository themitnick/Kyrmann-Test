package com.assure.role_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.flyway.enabled=false",
		"eureka.client.enabled=false",
		"management.tracing.sampling.probability=0.0"
})
class RoleServiceApplicationTests {

	@Test
	void contextLoads() {
		// Test que le contexte Spring se charge correctement
	}

}
