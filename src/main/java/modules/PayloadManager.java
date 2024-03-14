package modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import pojos.*;

public class PayloadManager {

    Gson gson;
    //JAVA-JSON

    public String createPayloadGSON() {
        Faker faker = new Faker();

        Booking booking = new Booking();
        String expectFirstname = faker.name().firstName();

        booking.setFirstname(expectFirstname);
        booking.setLastname("Charde");
        booking.setDepositpaid(true);
        booking.setTotalprice(999);
        booking.setAdditionalneeds("Breakfast");


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2024-01-04");

        booking.setBookingdates(bookingdates);

        System.out.println(booking);
        // Object -> JSON String (GSON)

         gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;

    }

    public void createpayloadJackson(){
        //Incomplete Method
    }

    public String updatePayload(){
        Booking booking = new Booking();
        booking.setFirstname("Sonali");
        booking.setLastname("Charde");
        booking.setTotalprice(10000);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Extra Matress");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2023-11-01");
        bookingdates.setCheckout("2023-11-12");

        booking.setBookingdates(bookingdates);
        System.out.println(booking);
        //Object - JSON String (GSON)
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonStringBooking = gson.toJson(auth);
        System.out.println(jsonStringBooking);
       return jsonStringBooking;
    }

    public String getTokenFromJSON(String tokenresponse){
        gson = new Gson();
        TokenResponse TokenResponse1 = gson.fromJson(tokenresponse, TokenResponse.class);
        return TokenResponse1.getToken();
    }
    public BookingResponse bookingResponseJava(String responseString){
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
    public Booking bookingResponsePUTReqJava(String responseString){
        gson = new Gson();
        Booking booking = gson.fromJson(responseString,Booking.class);
        return booking;
    }

}

