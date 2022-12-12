package project;

public class Client {

    private String Full_name_client;
    private String Phone_number;
    private String Email;
    private String Password;
    private String Branch;

    private int Client_id;

    public String getFull_name_client() {
        return Full_name_client;
    }

    public void setFull_name_client(String Full_name_client) {
        this.Full_name_client = Full_name_client;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String Phone_number) {
        this.Phone_number = Phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public int getClient_id() {
        return Client_id;
    }

    public void setClient_id(int Client_id) {
        this.Client_id = Client_id;
    }
}
