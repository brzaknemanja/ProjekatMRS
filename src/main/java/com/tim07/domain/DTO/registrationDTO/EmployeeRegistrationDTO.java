package com.tim07.domain.DTO.registrationDTO;

/**
 * Created by Katarina Cukurov on 19/04/2017.
 */
public class EmployeeRegistrationDTO {

    private String name;
    private String lastname;
    private String username;
    private String password;
    private String password2;
    private String dateOfBirth;
    private Integer dressSize;
    private Integer shoeSize;

    public EmployeeRegistrationDTO(){}

    public EmployeeRegistrationDTO(String name, String lastname, String username, String password, String password2, String dateOfBirth, Integer dressSize, Integer shoeSize) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.dateOfBirth = dateOfBirth;
        this.dressSize = dressSize;
        this.shoeSize = shoeSize;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword2() { return password2; }

    public void setPassword2(String password2) { this.password2 = password2; }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Integer getDressSize() { return dressSize; }

    public void setDressSize(Integer dressSize) { this.dressSize = dressSize; }

    public Integer getShoeSize() {return shoeSize; }

    public void setShoeSize(Integer shoeSize) { this.shoeSize = shoeSize; }
}
