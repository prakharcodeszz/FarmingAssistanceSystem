package com.fas.farmer.dtos;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    @Length(min=4, max=16, message = "First name must be b/w 4 and 16")
    private String firstName;
    @Column(nullable = false)
    @Length(min=4, max=16, message = "First name must be b/w 4 and 16")
    private String lastName;
    @Column(nullable = false)
    @Min(1)
    @Max(999999)
    private  Long pincode;
    @Column(nullable = false)
    @Min(1)
    @Max(999999999)
    private  Long phnNumber;

    @Override
    public String toString() {
        return "Supplier{" +
                "Id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pincode=" + pincode +
                ", phnNumber=" + phnNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return id.equals(supplier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Long getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(Long phnNumber) {
        this.phnNumber = phnNumber;
    }
}
