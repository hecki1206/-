package mini_project;


public class smhVO_signup {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String admin;
	
	public smhVO_signup() {
		super();
	}
	public smhVO_signup(String id) {
		super();
		this.id = id;
	}
	public smhVO_signup(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public smhVO_signup(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	public smhVO_signup(String id, String pw, String name, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}
	public smhVO_signup(String id, String pw, String name, String phone, String admin) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.admin = admin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "smhVO_signup [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", admin=" + admin
				+ "]";
	}
	
	
}
