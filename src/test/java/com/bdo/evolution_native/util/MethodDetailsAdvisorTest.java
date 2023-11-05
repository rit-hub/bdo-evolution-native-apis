package com.bdo.evolution_native.util;


import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * The type Method details advisor test.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MethodDetailsAdvisorTest {

    @Mock
    private Logger loggerMock;

    @InjectMocks
    private MethodLoggerAdvisor methodLoggerAdvisor;


    /**
     * Log method argument when arguments are null.
     *
     * @throws Throwable the throwable
     */
    @Test
    @DisplayName("Should not log method arguments when they are null")
    void logMethodArgumentWhenArgumentsAreNull() throws Throwable {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(null);
        try {
            methodLoggerAdvisor.methodDetails(joinPoint);
        } catch (Exception e) {
            assertNotNull(e);
        }
        verify(loggerMock, never()).info(anyString());
    }
}