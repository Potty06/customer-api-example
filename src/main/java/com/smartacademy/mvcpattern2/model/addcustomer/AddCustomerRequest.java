package com.smartacademy.mvcpattern2.model.addcustomer;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;

/**
 * addCustomerReq
 * <p>
 * Add a new Customer to the DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customer"
})
public class AddCustomerRequest {

    /**
     * the customer inserted into DB
     *
     */
    @JsonProperty("customer")
    @JsonPropertyDescription(" the customer inserted into DB")
    @Valid
    private AddCustomer customer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the customer inserted into DB
     *
     */
    @JsonProperty("customer")
    public AddCustomer getCustomer() {
        return customer;
    }

    /**
     * the customer inserted into DB
     *
     */
    @JsonProperty("customer")
    public void setCustomer(AddCustomer customer) {
        this.customer = customer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
