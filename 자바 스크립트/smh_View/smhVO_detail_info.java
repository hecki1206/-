package mini_project;

	public class smhVO_detail_info {
	   private String sub_code;
	   private String subject;
	   private String class_day;
	   private int class_time;

	   public smhVO_detail_info() {
	      super();
	   }
	   
	   
	   public smhVO_detail_info(String sub_code) {
	      super();
	      this.sub_code = sub_code;
	   }

	   
	   public smhVO_detail_info(String sub_code, String subject) {
	      super();
	      this.sub_code = sub_code;
	      this.subject = subject;
	   }


	   public smhVO_detail_info(String sub_code, String subject, String class_day) {
	      super();
	      this.sub_code = sub_code;
	      this.subject = subject;
	      this.class_day = class_day;
	   }


	   public smhVO_detail_info(String sub_code, String subject, String class_day, int class_time) {
	      super();
	      this.sub_code = sub_code;
	      this.subject = subject;
	      this.class_day = class_day;
	      this.class_time = class_time;
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
	   public String getClass_day() {
	      return class_day;
	   }
	   public void setClass_day(String class_day) {
	      this.class_day = class_day;
	   }
	   public int getClass_time() {
	      return class_time;
	   }
	   public void setClass_time(int class_time) {
	      this.class_time = class_time;
	   }
	   
	   
	}
