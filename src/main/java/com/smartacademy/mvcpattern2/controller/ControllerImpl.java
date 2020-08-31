package com.smartacademy.mvcpattern2.controller;

import com.smartacademy.mvcpattern2.model.addcustomer.AddCustomer;
import com.smartacademy.mvcpattern2.model.addcustomer.AddCustomerRequest;
import com.smartacademy.mvcpattern2.model.addcustomer.AddCustomerResponse;
import com.smartacademy.mvcpattern2.model.customermodel.Customer;
import com.smartacademy.mvcpattern2.model.errormodel.ErrorResponse;
import com.smartacademy.mvcpattern2.model.getcustomer.GetCustomer;
import com.smartacademy.mvcpattern2.model.getcustomer.GetCustomerResponse;
import com.smartacademy.mvcpattern2.model.updatecustomer.UpdateCustomer;
import com.smartacademy.mvcpattern2.model.updatecustomer.UpdateCustomerRequest;
import com.smartacademy.mvcpattern2.model.updatecustomer.UpdateCustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class ControllerImpl implements Controller {

    //@Value("${mvcpattern.annotationexample.valueofstring}")
    //private String annotationExample;
    private Logger log = (Logger) LoggerFactory.getLogger(Controller.class);

    private static List<Customer> customers = new ArrayList<>();
    private static Integer customerIdValue = 5;
    static {
        customers.add(new Customer(1, "Jack", LocalDate.now(), "NY", "jack@g.com", "1234"));
        customers.add(new Customer(2, "Marry", LocalDate.now(), "Alaska", "marry@g.com", "8087"));
        customers.add(new Customer(3, "John", LocalDate.now(), "CA", "john@g.com", "6565"));
        customers.add(new Customer(4, "Mike", LocalDate.now(), "NY", "mike@g.com", "12444"));
    }

    @Override
    public GetCustomerResponse getCustomers(Optional<Integer> customerId,
                                            Optional<String> phoneNumber,
                                            HttpServletResponse httpServletResponse) {
        //System.out.println("annotationExample = " + annotationExample);
        log.info("Called /getCustomer");
        log.trace("Called /getCustomer with customerId = " + customerId + "and phoneNumber = " + phoneNumber);
        log.debug("Called /getCustomer at " + LocalDate.now());
        // customerId case
        if(customerId.isPresent() && phoneNumber.isEmpty()){
            log.info("CustomerId is present");
            log.trace("CustomerId = " + customerId.get());
            log.debug("CustomerId present at" + LocalDate.now());
            //processing
            // create response list
            ArrayList<GetCustomer> responseList = new ArrayList<>();
            //customerId is found in the list
            for(Customer customer: customers){

                //customer found, send back response
                if(customer.getCustomerId().equals(customerId.get())){
                    GetCustomer response = new GetCustomer();
                    //preparing response
                    response.setCustomerId(customerId.get());
                    response.setCustomerName(customer.getCustomerName());
                    response.setDateOfBirth(customer.getDateOfBirth());
                    response.setAddress(customer.getAddress());
                    response.setEmail(customer.getEmail());
                    response.setPhoneNo(customer.getPhoneNo());
                    log.info("Customer found and added to the list");
                    log.debug("Customer found and added: " + response.toString());
                    //add to list
                    responseList.add(response);
                }

            }
            //check if there any customers
            if(responseList.isEmpty()){
                log.info("No customers found");
                log.debug("No customers found with customerId " + customerId.get());
                //no customers found
                httpServletResponse.setStatus(404);
                GetCustomerResponse response = new GetCustomerResponse();
                response.setResponseDescription("No entries found!");
                return response;
            }
            //if there are customers, send 200
            else {
                log.info("Returning list of customers");
                log.debug("Returning number of " + responseList.size() + " customers");
                httpServletResponse.setStatus(200);
                GetCustomerResponse response = new GetCustomerResponse();
                response.setGetCustomer(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        // phoneNumber case
        else if(customerId.isEmpty() && phoneNumber.isPresent()){
            //processing
            // create response list
            log.info("PhoneNo. is present");
            log.trace("PhoneNo. = " + phoneNumber.get());
            log.debug("PhoneNo. present at" + LocalDate.now());
            ArrayList<GetCustomer> responseList = new ArrayList<>();
            //customerId is found in the list
            for(Customer customer: customers){

                //customer found, send back response
                if(customer.getPhoneNo().equals(phoneNumber.get())){
                    GetCustomer response = new GetCustomer();
                    //preparing response
                    response.setCustomerId(customer.getCustomerId());
                    response.setCustomerName(customer.getCustomerName());
                    response.setDateOfBirth(customer.getDateOfBirth());
                    response.setAddress(customer.getAddress());
                    response.setEmail(customer.getEmail());
                    response.setPhoneNo(phoneNumber.get());
                    //add to list
                    log.info("Customer found and added to the list");
                    log.debug("Customer found and added: " + response.toString());
                    responseList.add(response);
                }
            }
            //check if there any customers
            if(responseList.isEmpty()){
                //no customers found
                log.info("No customers found");
                log.debug("No customers found with customerId " + phoneNumber.get());
                httpServletResponse.setStatus(404);
                GetCustomerResponse response = new GetCustomerResponse();
                response.setResponseDescription("No entries found!");
                return response;
            }
            //if there are customers, send 200
            else {
                log.info("Returning list of customers");
                log.debug("Returning number of " + responseList.size() + " customers");
                httpServletResponse.setStatus(200);
                GetCustomerResponse response = new GetCustomerResponse();
                response.setGetCustomer(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        //error case
        else{
            //processing
            log.info("No entity found!");
            httpServletResponse.setStatus(400);//specify that request is invalid
            GetCustomerResponse response = new GetCustomerResponse();
            response.setResponseDescription("Invalid Request");
            return response;
        }
    }

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest addCustomerRequest, HttpServletResponse httpServletResponse) {
        log.info("Called /addCustomer");
        log.trace("Called /addCustomer with request: " + addCustomerRequest.getCustomer());
        log.debug("Called /addCustomer at " + LocalDate.now());
        AddCustomer addCustomer = addCustomerRequest.getCustomer();
        //check if the customer already exists
        for (Customer customer: customers) {
            //if customer already exists, return duplicate status
            if(checkAddCustomer(addCustomer, customer)){
                log.info("Customer already exist");
                log.trace("Customer = " + addCustomerRequest.getCustomer());
                httpServletResponse.setStatus(httpServletResponse.SC_CONFLICT);
                AddCustomerResponse addCustomerResponse = new AddCustomerResponse();
                addCustomerResponse.setResponseDescription("Customer already exists!");
                return addCustomerResponse;
            }
        }

        //if not, add to the list

        Customer customer = new Customer();
        customer.setCustomerId(customerIdValue);

        customer.setCustomerName(addCustomer.getCustomerName());
        customer.setDateOfBirth(addCustomer.getDateOfBirth());
        customer.setAddress(addCustomer.getAddress());
        customer.setEmail(addCustomer.getEmail());
        customer.setPhoneNo(addCustomer.getPhoneNo());

        customers.add(customer);
        //send success response
        httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        AddCustomerResponse addCustomerResponse = new AddCustomerResponse();
        addCustomerResponse.setCustomerId(customerIdValue);
        customerIdValue++;
        addCustomerResponse.setResponseDescription("Customer added");
        log.info("Customer added to the list");
        log.debug("Customer found and added: " + addCustomerResponse.toString());
        return  addCustomerResponse;

    }

    @Override
    public ResponseEntity<?> updateCustomer(UpdateCustomerRequest customerRequest) {
        //find client in the list
        log.info("Called /updateCustomer");
        log.trace("Called /updateCustomer with request: " + customerRequest.getCustomer());
        log.debug("Called /updateCustomer at " + LocalDate.now());
        UpdateCustomer updateCustomer = customerRequest.getCustomer();
        //if found, make sure that the request is not identical to the entry
        for( Customer customer: customers ){
            if(updateCustomer.getCustomerId().equals(customer.getCustomerId())) {
                if (checkUpdate(updateCustomer, customer)) {
                    //if identical, return error message
                    log.info("Same Request");
                    log.trace("Customer = " + customerRequest.getCustomer());
                    UpdateCustomerResponse response = new UpdateCustomerResponse();
                    response.setResponseDescription("Request identical with entry");
                    return new ResponseEntity<UpdateCustomerResponse>(response, HttpStatus.CONFLICT);
                } else {
                    //if not identical, make update
                    customer.setCustomerName(updateCustomer.getCustomerName());
                    customer.setDateOfBirth(updateCustomer.getDateOfBirth());
                    customer.setAddress(updateCustomer.getAddress());
                    customer.setEmail(updateCustomer.getEmail());
                    customer.setPhoneNo(updateCustomer.getPhoneNo());
//                    UpdateCustomerResponse response = new UpdateCustomerResponse();
//                    response.setResponseDescription("Item updated");
//                    log.info("Customer updated successful");
//                    log.debug("Customer found and updated : " + response.toString());
//                    return new ResponseEntity<>(response, HttpStatus.OK);

                    ErrorResponse response = new ErrorResponse();
                    response.setErrorDescription("Item updated");
                    log.info("Customer updated successful");
                    log.debug("Customer found and updated : " + response.toString());
                    return new ResponseEntity<ErrorResponse>(response, HttpStatus.OK);
                }
            }
        }
        //in case customer was not found, return 404
        UpdateCustomerResponse response = new UpdateCustomerResponse();
        response.setResponseDescription("Customer with id" + updateCustomer.getCustomerId() + "was not found!");
        log.info("No customers found");
        log.debug("No customers found with customerDates: " + customerRequest.getCustomer());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    private boolean checkAddCustomer(AddCustomer customer1, Customer customer2){
        //validation for the mandatory fields
        if( customer1.getCustomerName().equals(customer2.getCustomerName()) &&
            customer1.getDateOfBirth().equals(customer2.getDateOfBirth()) &&
            customer1.getEmail().equals(customer2.getEmail())){
            return true;
        }else
            return false;

    }

    private boolean checkUpdate(UpdateCustomer customer1, Customer customer2){
        if( customer1.getCustomerName().equals(customer2.getCustomerName()) &&
            customer1.getEmail().equals(customer2.getEmail()) &&
            customer1.getPhoneNo().equals(customer2.getPhoneNo())){
            return true;
        }else
            return false;

    }
}
