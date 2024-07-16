package com.gorest.crudtest;

import com.github.javafaker.Faker;
import com.gorest.goreststeps.UserSteps;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    public static int userId;

    @Steps
    UserSteps userSteps;

    @Title("Getting the list of users from the database")
    @Test
    public void test01(){
        ValidatableResponse response = userSteps.gettingListOfUsers();
        response.statusCode(200);
        response.log().all();
    }

    @Title("Creating the new user.")
    @Test
    public void test02(){
        Faker faker = new Faker();
        ValidatableResponse response = userSteps.createNewUser(faker.name().fullName(), faker.internet().emailAddress(), "male","active");
        response.statusCode(201);
        userId = response.extract().path("id");
        System.out.println("New user created with ID: "+userId);
    }

    @Title("Getting info of newly added user.")
    @Test
    public void test03(){
        ValidatableResponse response = userSteps.getUserInfoById(userId);
        response.statusCode(200);
        response.log().all();
        response.extract().path("id", String.valueOf(equalTo(userId)));
    }

    @Title("Updating the user by ID")
    @Test
    public void test04(){
        Faker faker = new Faker();
        ValidatableResponse response = userSteps.updateUserById(userId,faker.internet().emailAddress(),"inactive");
        response.statusCode(200);
        response.log().all();
        response.extract().path("id", String.valueOf(equalTo(userId)),"status","inactive");
    }

    @Title("Deleting user by ID")
    @Test
    public void test05(){
        ValidatableResponse response = userSteps.deleteUserById(userId);
        response.statusCode(204);
        response.log().all();
    }

    @Title("Verifying user is deleted using ID.")
    @Test
    public void test06(){
        ValidatableResponse response = userSteps.getUserInfoById(userId);
        response.statusCode(404);
        response.log().all();
    }
}