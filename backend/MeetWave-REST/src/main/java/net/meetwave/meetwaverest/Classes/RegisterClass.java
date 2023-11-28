package net.meetwave.meetwaverest.Classes;

public class RegisterClass {
    //{
    //     "username": "JackieX99",
    //         "email": "foldvaria03@gmail.com",
    //       "password": "Teszt123",
    //         "phone": "06708845584"
    // }


    String fullname;

    String email;

    String password;

    String phone;

    public RegisterClass() {

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
