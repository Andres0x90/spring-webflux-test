package co.com.sofka.Spring.Webflux.Test;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import static org.junit.jupiter.api.Assertions.*;

class UppercaseConverterTest {
    final TestPublisher<String> testPublisher = TestPublisher.create();

    @Test
    void testUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }

    @Test
    void testNullPointer()
    {
        TestPublisher
                .createNoncompliant(TestPublisher.Violation.ALLOW_NULL)
                .emit("1", "2", null, "3");
    }
}