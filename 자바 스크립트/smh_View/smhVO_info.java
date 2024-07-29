package mini_project;

public class smhVO_info {
	private String sub_code;
	private String subject;
	
	
	
	public smhVO_info() {
		super();
	}
	public smhVO_info(String sub_code) {
		super();
		this.sub_code = sub_code;
	}
	public smhVO_info(String sub_code, String subject) {
		super();
		this.sub_code = sub_code;
		this.subject = subject;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
