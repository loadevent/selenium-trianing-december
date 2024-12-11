package utilities;

public class Employee {
    private String firstname,middlename,lastname;
    private String username,password;
    public Employee(){}
    public Employee(String firstname, String middlename, String lastname, String username, String password) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public Employee setFirstname(String firstname) {
        this.firstname = firstname;return this;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Employee setMiddlename(String middlename) {
        this.middlename = middlename;return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Employee setLastname(String lastname) {
        this.lastname = lastname;return this;
    }

    public String getUsername() {
        return username;
    }

    public Employee setUsername(String username) {
        this.username = username;return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }
}
