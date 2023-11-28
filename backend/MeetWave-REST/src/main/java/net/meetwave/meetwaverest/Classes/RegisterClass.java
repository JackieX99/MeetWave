package net.meetwave.meetwaverest.Classes;

public class RegisterClass {
    //{
    //     "username": "JackieX99",
    //         "email": "foldvaria03@gmail.com",
    //       "password": "Teszt123",
    //         "phone": "06708845584"
    // }


    String username;

    String email;

    String password;

    int phone;

    public RegisterClass() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
