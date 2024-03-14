package tests.integration;

import base.BaseTest;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import modules.PayloadManager;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pojos.Booking;
import pojos.BookingResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TCIntegration1 extends BaseTest {
    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking


    // How to pass the data to one testcase to another.

//    @Test
//    public void createToken(){
//      Provided in basetest
//    }



    @Test(groups = "integration", priority = 1)
    @Owner("Sonali")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")

    public void testCreateBooking(ITestContext iTestContext) {
        //POST
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();

        validatableResponse = response.then().log().all();
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
        iTestContext.setAttribute("token", getToken());
    }

    @Test(groups = "integration", priority = 2)
    @Owner("Sonali")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")

    public void testVerifyBookingID(ITestContext iTestContext){
        //GET Req
        System.out.println(iTestContext.getAttribute("bookingid"));
        assertThat("bookingid").isNotNull();
    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")

    public void testUpdateBookingByID(ITestContext iTestContext) {
        //PUT/PATCH req
        Integer bookingID = (Integer)iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        System.out.println("booking ID is "+bookingID);
        System.out.println("Token is "+token);

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID);

        response = RestAssured.given().spec(requestSpecification)
                .cookie("token",token)
                .body(payloadManager.updatePayload()).put();

        validatableResponse = response.then().log().all();
        Booking booking = payloadManager.bookingResponsePUTReqJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Sonali");
    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")

    public void testDeleteBooking(ITestContext iTestContext){
        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        //Delete Req
        System.out.println(bookingID);
        System.out.println(token);
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+
                bookingID).cookie(token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }

}
