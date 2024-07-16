package com.gorest.goreststeps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class UserSteps {

    @Step("Getting list of users")
    public ValidatableResponse gettingListOfUsers(){
        return SerenityRest.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_USER_LIST)
                .then();
    }

    @Step("Creating new user with Name: {0}, Email: {1}, Gender: {2}, Status: {3}")
    public ValidatableResponse createNewUser(String uname, String uemail, String ugender, String ustatus){
        UserPojo userPojo = UserPojo.setUserPojo(uname,uemail,ugender,ustatus);

        return SerenityRest.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer a7af65a61901b2f5583471341dbc751b1f4ab8546bb10e5ebce7c255404fab72")
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_NEW_USER)
                .then();
    }

    @Step("Getting newly added user details by providing id : {0}")
    public ValidatableResponse getUserInfoById(int userId){
        return SerenityRest.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer a7af65a61901b2f5583471341dbc751b1f4ab8546bb10e5ebce7c255404fab72")
                .when()
                .get(EndPoints.GET_USER_BY_ID, userId)
                .then();
    }

    @Step("Updating the user details email - {1} and status - {2} by providing id {0}")
    public ValidatableResponse updateUserById(int userId, String uemail, String ustatus){
        UserPojo userPojo = UserPojo.setUserPojoPatchMethod(uemail,ustatus);

        return SerenityRest.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer a7af65a61901b2f5583471341dbc751b1f4ab8546bb10e5ebce7c255404fab72")
                .when()
                .body(userPojo)
                .get(EndPoints.UPDATE_USER_BY_ID, userId)
                .then();
    }

    @Step("Deleting user by ID : {0}")
    public ValidatableResponse deleteUserById(int userId){
        return SerenityRest.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer a7af65a61901b2f5583471341dbc751b1f4ab8546bb10e5ebce7c255404fab72")
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID, userId)
                .then();
    }
}
