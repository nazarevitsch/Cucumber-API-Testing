import entities.Login;
import entities.Producer;
import entities.Registration;
import entities.Token;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.List;

public class ApiTestingSteps {
    private long id;
    private Response response;
    private Login login;
    private Registration registration;
    private Producer producer;

    @Given("producer id is {long}")
    public void givenProducerId(long id) {
        this.id = id;
    }

    @When("user try to get producer by id")
    public void getProducerById() {
        response = new ApiEndpoints().getProducerById(id);
    }

    @Then("user receive status code {int}")
    public void compareStatusCodes(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("response don't equal zero")
    public void checkThatResponseNotNull() {
        response.then().body(Matchers.notNullValue());
    }


    @Given("user try to login with next data:")
    public void givenDataToLogin(List<List<String>> list) {
        login = new Login(list.get(1).get(0), list.get(1).get(1));
    }

    @When("user try to login")
    public void userLogin() {
        response = new ApiEndpoints().loginUser(login);
    }

    @Given("user try to register with next data:")
    public void givenDataToRegistration(List<List<String>> list) {
        registration = new Registration(list.get(1).get(0), list.get(1).get(1), list.get(1).get(2));
    }

    @When("user try to register")
    public void userRegister() {
        response = new ApiEndpoints().registrationUser(registration);
    }

    @Given("user try to create producer with next data:")
    public void givenDataToProducer(List<List<String>> list) {
        producer = new Producer().setImage(list.get(1).get(0)).setNew_image(list.get(1).get(1))
                .setProducer_name(list.get(1).get(2)).setTypes(list.get(1).get(3))
                .setSteps(list.get(1).get(4)).setRegion_id(list.get(1).get(5))
                .setDescription(list.get(1).get(6));
    }

    @When("user try to create producer")
    public void userCreateNewProducer() {
        response = new ApiEndpoints().addProducer(producer, response.body().as(Token.class).getToken());
    }

    @When("unauthorized user try to create producer")
    public void unauthorizedUserCreateNewProducer() {
        response = new ApiEndpoints().addProducer(producer, "TOKEN");
    }
}
