public class Admin extends User {
    private final String role;
    public Admin(String name, String email, String phone, String password) {
        super(name, email, phone, password);
        this.role = "ADMIN";
    }
    public String getRole() { return role; }
    @Override public String toString() { return super.toString() + " | " + role; }
}
