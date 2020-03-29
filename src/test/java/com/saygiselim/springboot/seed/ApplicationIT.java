package com.saygiselim.springboot.seed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("test")
public class ApplicationIT {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void Application_Should_Load_Context_When_Started() {
        assertThat(applicationContext.getId()).isEqualTo("application");
    }
}