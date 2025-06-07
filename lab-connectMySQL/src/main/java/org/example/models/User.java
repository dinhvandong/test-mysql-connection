package org.example.models;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    private int id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits and may start with +"
    )
    private String phone;

    private String address;

    private LocalDate birthDay;

    @NotBlank(message = "Role is required")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Full name is required") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Full name is required") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String password) {
        this.password = password;
    }

    public @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits and may start with +"
    ) String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be between 10 and 15 digits and may start with +"
    ) String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public @NotBlank(message = "Role is required") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required") String role) {
        this.role = role;
    }


    // Getters and Setters
}
