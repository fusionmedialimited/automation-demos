import POJOs.Pet;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static POJOs.Pet.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests extends RestAssuredSetUp {

    @Test
    @Order(1)
    public void createPet_test() {
        // create the payload for the request
        Pet payload = new Pet();

        payload.setId(1);
        payload.setName("Bob");
        payload.setCategory(new Category(1,"cat"));
        payload.setStatus("available");

        /* create new pet record via POST request with the payload above and verify that
        the response shows exactly the same field value as were specified in the payload */
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .log()
                .ifValidationFails(LogDetail.ALL)
        .when()
                .post("pet")
        .then()
                .log()
                .ifValidationFails(LogDetail.ALL)
        .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "category.name", equalTo("cat"),
                        "name", equalTo("Bob"),
                        "status", equalTo("available")
                );
    }

    @Test
    @Order(2)
    public void getCreatedPet_test() {
        given()
                .contentType(ContentType.JSON)
                .log()
                .ifValidationFails(LogDetail.ALL)
        .when()
                .get("pet/1")
        .then()
                .log()
                .ifValidationFails(LogDetail.ALL)
        .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1),
                        "name", equalTo("Bob"),
                        "status", equalTo("available")
                );
    }

}
