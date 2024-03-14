package tests.crud;

import base.BaseTest;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class testCreateBooking extends BaseTest {
    @Test
    @Owner("Sonali")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1- Verify that the Booking can be Created")
    public void testCreateBooking(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Validatable Default
        validatableResponse.statusCode(200);
        //assertJ
        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
    }

    @Test
    @Owner("Sonali")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#2 - Verify that the Booking not created when Payload is missing")
    public void testCreateBookingNegative(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response =RestAssured.given().spec(requestSpecification)
                .when().body("{}").post();
        validatableResponse = response.then().log().all();
        //TestNG Assertions
        assertActions.verifyStatusCodeInvalidReq(response);
    }
}
