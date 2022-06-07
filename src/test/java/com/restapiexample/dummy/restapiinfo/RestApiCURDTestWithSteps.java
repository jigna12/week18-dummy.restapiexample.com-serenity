package com.restapiexample.dummy.restapiinfo;

import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class RestApiCURDTestWithSteps extends TestBase {

    static String employee_name = "Tiger Nixon" + TestUtils.getRandomValue();
    static String employee_age = "61" + TestUtils.getRandomValue();
    static String employee_salary = "320800";
    static String profile_image = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int employeeId;

    @Steps
    RestApiSteps restApiSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        restApiSteps.createEmployee(employee_name,employee_salary,employee_age,profile_image).statusCode(200).log().all();

    }
    @Title("Verify if the employee was added to the application")
    @Test
    public void test002(){

        HashMap<String, Object> employeeMap = restApiSteps.getEmployeeInfoByFirstname(employee_name);
        Assert.assertThat(employeeMap, hasValue(employee_name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);

    }
    @Title("Update the employee information and verify the updated information")
    @Test
    public void test003(){
        employee_name = "Ashton Cox";
        employee_age = "45";
        employee_salary = "55000";
        profile_image = "";
        employeeId = 5037;
        restApiSteps.updateEmployee(employeeId, employee_name, employee_age,employee_salary,profile_image).log().all().statusCode(200);


    }
    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004(){
        employeeId = 5037;
        restApiSteps.deleteEmployee(employeeId).statusCode(204);
       restApiSteps.getEmployeeById(employeeId).statusCode(404);
    }

}
