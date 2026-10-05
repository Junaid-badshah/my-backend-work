package com.example.book.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestingFrameworkConfigTest {

    interface DummyService {
        String getStatus();
    }

    @Mock
    private DummyService dummyService;

    @Test
    @DisplayName("Testing Framework Verification - JUnit5, Mockito, AssertJ")
    void testFrameworkConfiguration() {
        // Mockito behavior setup
        when(dummyService.getStatus()).thenReturn("FRAMEWORK_READY");

        // Execution & AssertJ Assertion
        String status = dummyService.getStatus();
        assertThat(status).isNotNull().isEqualTo("FRAMEWORK_READY");
    }
}