package employees;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesResourceTest {

    @BeforeEach
    void initRestAssured() {
        // Környezeti változóval paraméterezhetővé tenni
        RestAssured.baseURI = "http://localhost:8080/employees-web-1.0-SNAPSHOT/api";
        RestAssured.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
    }

    @Test
    void create() {
//        var name = "John Doe" + UUID.randomUUID();
//        System.out.println("Name: " + name);
//        given().
//                body(
//                        // language=json
//                        """
//                        {
//                          "name": "%s"
//                        }
//                        """.formatted(name))
//                        .when()
//                .post("/employees")
//                .then()
//                .statusCode(201)
//                .body("name", equalTo(name));

        var name = "John Doe " + UUID.randomUUID();
        var input = new EmployeeDto(null, name);
        var output = given()
                .body(input)
                .post("/employees")
                .then()
                .statusCode(201)
                .extract()
                .as(EmployeeDto.class);

        assertEquals(name, output.name());
    }

}
