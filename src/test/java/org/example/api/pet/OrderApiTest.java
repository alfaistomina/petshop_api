package org.example.api.pet;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.model.OrderPet;
import org.example.model.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class OrderApiTest {

    //public OrderPet orderPet;
    final int ID = new Random().nextInt(10);
    final int PET_ID = new Random().nextInt(10);
    final int STATUS_RANDOM = new Random().nextInt(3);
    final boolean COMPLETE = new Random().nextBoolean();
    final int QUANTITY = new Random().nextInt(10);

    @BeforeClass
    public void prepare() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", System.getProperty("api.key"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());
    }

    @Test
    public void getOrderPetTest() {
        OrderPet orderPet = new OrderPet();
        orderPet.setId(ID);
        orderPet.setPetId(PET_ID);
        orderPet.setQuantity(QUANTITY);
        orderPet.setShipDate("2021-11-09T10:49:03.494Z");
        Enum[] statusValue = {Status.available, Status.pending, Status.sold};
        orderPet.setStatus((Status) statusValue[STATUS_RANDOM]);
        orderPet.setComplete(COMPLETE);

        given()
                .body(orderPet)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
        OrderPet actual =
                given()
                        .pathParam("orderId", ID)
                        .when()
                        .get("/store/order/{orderId}")
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(OrderPet.class);
        Assert.assertEquals(actual.getId(), orderPet.getId());
    }
    @Test(priority = 1)
    public void deleteOrderTest() throws InterruptedException {
        given()
                .pathParam("orderId", ID)
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
        given()
                .pathParam("orderId", ID)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void getInventoryTest() {
        Map<String, Object> map = given()
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .extract().jsonPath().getMap("",String.class, Object.class);

        Assert.assertTrue(map.containsKey("sold"),"Inventory не содержит статус sold");
    }
    //    public OrderApiTest() {
//        this.orderPet = getOrderPet();
//    }
}
