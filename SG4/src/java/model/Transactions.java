package model;

public class Transactions {

    private int id;
    private String status;
    private String user_session;
    private String user_name;
    private String user_mail;
    private String user_phone;
    private String address;
    private String amount;
    private String payment;
    private String message;
    private String created;
    private String admin_id;
    private boolean confirm;

    public Transactions() {

    }

    public Transactions(int id, String status, String user_session, String user_name, String user_mail, String user_phone, String address, String amount, String payment, String message, String created, String admin_id, boolean confirm) {
        this.id = id;
        this.status = status;
        this.user_session = user_session;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_phone = user_phone;
        this.address = address;
        this.amount = amount;
        this.payment = payment;
        this.message = message;
        this.created = created;
        this.admin_id = admin_id;
        this.confirm = confirm;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_session() {
        return user_session;
    }

    public void setUser_session(String user_session) {
        this.user_session = user_session;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
   

    @Override
    public String toString() {
        return "Transactions{" + "id=" + id + ", status=" + status + ", user_session=" + user_session + ", user_name=" + user_name + ", user_mail=" + user_mail + ", user_phone=" + user_phone + ", address=" + address + ", amount=" + amount + ", payment=" + payment + ", message=" + message + ", created=" + created + ", admin_id=" + admin_id + ", confirm=" + confirm + '}';
    }

    public Object getCreateDate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
