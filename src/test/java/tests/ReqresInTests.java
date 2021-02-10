package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @DisplayName("SINGLE USER")
    void singleUserTest() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("data.first_name:", is("Janet"))
                .body("support.url", is("https://reqres.in/#support-heading"));

    }

        @Test
        @DisplayName("CREATE USER")
        void createUserTest() {
            given()
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .log()
                    .body()
                    .body("data.email", is("george.bluth@reqres.in"))
                    .body("data.avatar", is("https://reqres.in/img/faces/1-image.jp"));

    }

        @Test
        @DisplayName("UPDATE")
        void updateTest() {
            given()
                    .when()
                    .post("/users/2")
                    .then()
                    .statusCode(200)
                    .log()
                    .body()
                    .body("data.name", is("morpheus"))
                    .body("data.job", is("zion resident"));

    }

        @Test
        @DisplayName("UNSUCCESSFUL REGISTER")
        void unsuccessfulRegisterTest() {
            given()
                    .when()
                    .post("/api/register")
                    .then()
                    .statusCode(400)
                    .log()
                    .body()
                    .body("data.error", is("Missing password"));

    }

        @Test
        @DisplayName("DELETE USER")
        void delayedResponseTest() {
            given()
                    .when()
                    .delete("/api/users/2")
                    .then()
                    .statusCode(204);

    }

}
