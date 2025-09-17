package com.skillbarter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:5432/skillbarter_test",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class SkillBarterApplicationTests {

    @Test
    void contextLoads() {
        // Test that the application context loads
    }
}
