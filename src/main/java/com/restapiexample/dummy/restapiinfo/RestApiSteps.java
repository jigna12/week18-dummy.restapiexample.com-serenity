package com.restapiexample.dummy.restapiinfo;


import com.restapiexample.dummy.constants.EndPoints;
import com.restapiexample.dummy.model.RestApiPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;


public class RestApiSteps {

    @Step("Creating restApi with employee_name : {0}, employee_salary: {1}, employee_age: {2}, profile_image: {3}")
    public ValidatableResponse createEmployee(String employee_name, String employee_salary, String employee_age,
                                             String profile_image) {
        RestApiPojo restApiPojo = new RestApiPojo();
        restApiPojo.setEmployee_name(employee_name);
        restApiPojo.setEmployee_salary(employee_salary);
        restApiPojo.setEmployee_age(employee_age);
        restApiPojo.setProfile_image(profile_image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(restApiPojo)
                .when()
                .post(EndPoints.CREATE_EMPLOYEE)
                .then();
    }

    @Step("Getting the restApi information with employee_name: {0}")
    public HashMap<String, Object> getEmployeeInfoByFirstname(String employee_name) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> employeeMap = SerenityRest.given().log().all()//inline use
                .when()
                .get(EndPoints.GET_ALL_EMPLOYEE)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + employee_name + p2);
        return employeeMap;
    }

    @Step("Updating restApi information with employee_name : {0}, employee_salary: {1}, employee_age: {2}, profile_image: {3}")
    public ValidatableResponse updateEmployee(int employeeId, String employee_name, String employee_salary, String employee_age,
                                              String profile_image) {
        RestApiPojo restApiPojo = new RestApiPojo();
        restApiPojo.setEmployee_name(employee_name);
        restApiPojo.setEmployee_salary(employee_salary);
        restApiPojo.setEmployee_age(employee_age);
        restApiPojo.setProfile_image(profile_image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("employeeID", employeeId)
                .body(restApiPojo)
                .when()
                .put(EndPoints.UPDATE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Deleting restApi information with EmployeeId: {0}")
    public ValidatableResponse deleteEmployee(int employeeId){
        return SerenityRest.given().log().all()
                .pathParam("EmployeeID", employeeId)
                .when()
                .delete(EndPoints.DELETE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Getting restApi information with EmployeeId: {0}")
    public ValidatableResponse getEmployeeById(int employeeId){
       return SerenityRest.given().log().all()
                .pathParam("EmployeeID", employeeId)
                .when()
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then();
    }

}