import org.junit.jupiter.api.BeforeAll;

import io.restassured.config.EncoderConfig;

import static io.restassured.RestAssured.*;

public class RestAssuredSetUp {

    @BeforeAll
    public static void restAssuredSetUp() {

        baseURI = "https://petstore.swagger.io/v2";
        config = config()
                .encoderConfig(EncoderConfig
                        .encoderConfig()
                        .defaultContentCharset("UTF-8"));
    }

}
