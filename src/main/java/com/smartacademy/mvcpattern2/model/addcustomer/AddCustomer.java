package com.smartacademy.mvcpattern2.model.addcustomer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;


/**
 * the customer inserted into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customerName",
        "dateOfBirth",
        "address",
        "email",
        "phoneNo"
})
public class AddCustomer {

    /**
     * Name of the customer.
     */
    @JsonProperty("customerName")
    @JsonPropertyDescription(" Name of the customer.")
    @NotNull
    @Size(min = 10, message = "Invalid name")
    private String customerName;
    /**
     * The date of birth. Format YYYY-MM-DD.
     */
    @NotNull
    @Past
    @JsonProperty("dateOfBirth")
    @JsonPropertyDescription(" The date of birth. Format YYYY-MM-DD.")
    private LocalDate dateOfBirth;
    /**
     * The customer address.
     */
    @JsonProperty("address")
    @JsonPropertyDescription(" The customer address.")
    private String address;
    /**
     * The customer email.
     */
    @JsonProperty("email")
    @JsonPropertyDescription(" The customer email.")
    @NotNull
    @Size(min = 8)
    @Email(message = "Nu e bine sefule")
    private String email;
    /**
     * The phone number of the customer.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the customer. ")
    @Size(min = 9, max = 13, message = "Phone no should be from 2 and 34 digits maxim")
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Name of the customer.
     */
    @JsonProperty("customerName")
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Name of the customer.
     */
    @JsonProperty("customerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * The date of birth. Format YYYY-MM-DD.
     */
    @JsonProperty("dateOfBirth")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * The date of birth. Format YYYY-MM-DD.
     */
    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * The customer address.
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * The customer address.
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The customer email.
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * The customer email.
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The phone number of the customer.
     */
    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * The phone number of the customer.
     */
    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString() {
        return "AddCustomer{" +
                "customerName='" + customerName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}