package com.example.online_shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class OnlineShopApplicationTests {
    
    @Test
    void simpleTest() {
        System.out.println("Тест работает");
    }
}