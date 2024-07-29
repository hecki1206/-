package mini_project;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

public class smhDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	private void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
			String member = "sc_21K_bigdata11_p1_1";
			String password = "smhrd1";
			conn = DriverManager.getConnection(url, member, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(smhVO_signup smhVO_signup) {
		int result = 0;
		try {
			getConnection();
			String sql = "insert into Sign_up values(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, smhVO_signup.getPw());
			psmt.setString(3, smhVO_signup.getName());
			psmt.setString(4, smhVO_signup.getPhone());
			psmt.setString(5, smhVO_signup.getAdmin());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int login(smhVO_signup smhVO_signup) {
		int result = 0;
		try {
			getConnection();
			String sql = "select admin from Sign_up where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, smhVO_signup.getPw());
			rs = psmt.executeQuery();
			String admin = "";
			if (rs.next()) {
				admin = rs.getString("admin");
			}
			if (admin.equals("0")) {
				result = 1;
			} else if (admin.equals("1")) {
				result = 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int delete(smhVO_signup smhVO_signup) {
		int result = 0;
		try {
			getConnection();
			String sql = "delete from Sign_up where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, smhVO_signup.getPw());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int update(smhVO_signup smhVO_signup) {
		int result = 0;
		try {
			getConnection();
			String sql = "update Sign_up set pw = ?, name = ?, phone = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getPw());
			psmt.setString(2, smhVO_signup.getName());
			psmt.setString(3, smhVO_signup.getPhone());
			psmt.setString(4, smhVO_signup.getId());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int insert(smhVO_learning smhVO_learning) {
		int result = 0;
		try {
			getConnection();
			String sql = "insert into Sign_up values(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_learning.getId());
			psmt.setString(2, smhVO_learning.getSub_code());
			psmt.setString(3, smhVO_learning.getSubject());
			psmt.setString(4, smhVO_learning.getClass_day());
			psmt.setInt(5, smhVO_learning.getClass_time());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int delete(smhVO_learning smhVO_learning) {
		int result = 0;
		try {
			getConnection();
			String sql = "delete from Sign_up where id = ? and sub_code = ? and class_day = ? and class_time = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_learning.getId());
			psmt.setString(2, smhVO_learning.getSub_code());
			psmt.setString(3, smhVO_learning.getSubject());
			psmt.setString(4, smhVO_learning.getClass_day());
			psmt.setInt(5, smhVO_learning.getClass_time());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public int update(smhVO_learning smhVO_learning) {
		int result = 0;
		try {
			getConnection();
			String sql = "update from Sign_up set sub_code = ?, where id = ? and class_day = ? and class_time = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_learning.getId());
			psmt.setString(2, smhVO_learning.getSub_code());
			psmt.setString(3, smhVO_learning.getSubject());
			psmt.setString(4, smhVO_learning.getClass_day());
			psmt.setInt(5, smhVO_learning.getClass_time());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public ArrayList<smhVO_signup> info() {
		ArrayList<smhVO_signup> result1 = new ArrayList<>();
		try {
			getConnection();
			String sql = "select id, pw, name, phone, admin from sign_up";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String smhId = rs.getString("id");
				String smhPw = rs.getString("pw");
				String smhName = rs.getString("name");
				String smhPhone = rs.getString("phone");
				String smhAdmin = rs.getString("admin");
				result1.add(new smhVO_signup(smhId, smhPw, smhName, smhPhone, smhAdmin));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result1;
	}

	public ArrayList<smhVO_detail_info> detail_info() {
		ArrayList<smhVO_detail_info> result = new ArrayList<>();
		try {
			getConnection();
			String sql = "select sub_code, subject, class_day, class_time from detail_info";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String smhSub_code = rs.getString("sub_code");
				String smhSubject = rs.getString("subject");
				String smhClass_day = rs.getString("class_day");
				int smhClass_time = rs.getInt("class_time");
				result.add(new smhVO_detail_info(smhSub_code, smhSubject, smhClass_day, smhClass_time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public ArrayList<smhVO_learning> smhVO_learning() {
		ArrayList<smhVO_learning> result = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from learning";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String smhId = rs.getString("id");
				String smhSub_code = rs.getString("sub_code");
				String smhSubject = rs.getString("subject");
				String smhClass_day = rs.getString("class_day");
				int smhClass_time = rs.getInt("class_time");
				result.add(new smhVO_learning(smhId, smhSub_code, smhSubject, smhClass_day, smhClass_time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public ArrayList<smhVO_detail_info> schedule(String day) {
		ArrayList<smhVO_detail_info> result = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from detail_info where class_day = ?";
			psmt = conn.prepareStatement(sql);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, day);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String smhSub_code = rs.getString("sub_code");
				String smhSubject = rs.getString("subject");
				String smhClass_day = rs.getString("class_day");
				int smhClass_time = rs.getInt("class_time");
				result.add(new smhVO_detail_info(smhSub_code, smhSubject, smhClass_day, smhClass_time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}

	public void xin(smhVO_signup smhVO_signup, smhVO_learning smhVO_learning, String day, String x) {

		try {
			getConnection();
			String sql = "insert into learning values(?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, smhVO_learning.getSub_code());
			psmt.setString(3, smhVO_learning.getSubject());
			psmt.setString(4, day);
			psmt.setString(5, x);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

	public String current_time(String day) {
		String return1 = "";
		switch (day) {
		case "00":
		case "01":
		case "02":
		case "03":
		case "04":
		case "05":
		case "06":
		case "07":
		case "08":
			return1 = "1";
			break;
		case "09":
			return1 = "2";
			break;
		case "10":
			return1 = "3";
			break;
		case "11":
			return1 = "4";
			break;
		case "12":
			return1 = "5";
			break;
		case "13":
			return1 = "6";
			break;
		case "14":
			return1 = "7";
			break;
		case "15":
			return1 = "8";
			break;
		case "16":
			return1 = "9";
			break;
		default:
			return1 = "1";
			break;
		}
		return return1;
	}

	public smhVO_learning subj(String day, String x) {
		smhVO_learning smhVO_learning = new smhVO_learning();
		try {
			getConnection();
			String sql = "select sub_code, subject, class_day from detail_info where class_day =? and class_time =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, day);
			psmt.setString(2, x);
			rs = psmt.executeQuery();
			if (rs.next()) {
				smhVO_learning.setSub_code(rs.getString("sub_code"));
				smhVO_learning.setSubject(rs.getString("subject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return smhVO_learning;
	}

	public String findX(smhVO_signup smhVO_signup, String day) {
		String findX = "";
		try {
			getConnection();
			String sql = "select * from learning where id = ? and class_day =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, day);
			rs = psmt.executeQuery();
			if (rs.next()) {
				findX = rs.getString("class_time");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return findX;
	}

	public void CTX(smhVO_signup smhVO_signup, String x, String y, String day) {
		ArrayList<smhVO_detail_info> a = new ArrayList<smhVO_detail_info>();
		int i = 0;
		try {
			getConnection();
			String sql = "select * from detail_info where class_time > ? and class_time <=? and class_day = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, x);
			psmt.setString(2, y);
			psmt.setString(3, day);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String smhSub_code = rs.getString("sub_code");
				String smhSubject = rs.getString("subject");
				a.add(new smhVO_detail_info(smhSub_code, smhSubject));
			}
			int k = Integer.parseInt(x);
			for (i = 0; i != a.size(); i++) {
				sql = "insert into learning values(?,?,?,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, smhVO_signup.getId());
				psmt.setString(2, a.get(i).getSub_code());
				psmt.setString(3, a.get(i).getSubject());
				psmt.setString(4, day);
				psmt.setString(5, String.valueOf(k + i + 1));
				psmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

	public boolean check(smhVO_signup smhVO_signup, String day) {
		boolean a = true;
		try {
			getConnection();
			String sql = "select * from learning where id = ? and class_day =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			psmt.setString(2, day);
			rs = psmt.executeQuery();
			if (rs.next()) {
				a = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return a;

	}

	public void CC(smhVO_signup smhVO_signup, String day) {
		ArrayList<smhVO_learning> java = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> database = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> html = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> python = new ArrayList<smhVO_learning>();
		ResultSet rs2 = null;

		try {
			getConnection();
			String sql = "select TO_CHAR(class_day, 'YY-MM-DD') as class_day, count(class_time) from learning where id = ? group by class_day";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, smhVO_signup.getId());
			rs = psmt.executeQuery();
			while (rs.next()) {
				String day1 = rs.getString("class_day").split("\\.")[0];
				sql = "select subject, count(class_time) as c from learning where class_day=? group by subject";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, day1);
				rs2 = psmt.executeQuery();
				while (rs2.next()) {
					String a = rs.getString("class_day");
					String b = rs2.getString("subject");
					int c = rs2.getInt("c");
					smhVO_learning abc = new smhVO_learning(smhVO_signup.getId(), "", b, a, c);
					if (rs2.getString("subject").equals("DataBase")) {
						database.add(abc);
					} else if (rs2.getString("subject").equals("PYTHON")) {
						python.add(abc);
					} else if (rs2.getString("subject").equals("JAVA")) {
						java.add(abc);
					} else if (rs2.getString("subject").equals("HTML/CSS")) {
						html.add(abc);
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		System.out.println("DATABASE");
		for (int i = 0; i != database.size(); i++) {
			System.out.println("\t" + database.get(i).getClass_day() + "\t" + database.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + database.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("HTML/CSS");
		for (int i = 0; i != html.size(); i++) {
			System.out.println("\t" + html.get(i).getClass_day() + "\t" + html.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + html.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("JAVA");
		for (int i = 0; i != java.size(); i++) {
			System.out.println("\t" + java.get(i).getClass_day() + "\t" + java.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + java.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("Python");
		for (int i = 0; i != python.size(); i++) {
			System.out.println("\t" + python.get(i).getClass_day() + "\t" + python.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + python.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
	}
	
	public void All_student(String day) {
		ArrayList<smhVO_learning> java = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> database = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> html = new ArrayList<smhVO_learning>();
		ArrayList<smhVO_learning> python = new ArrayList<smhVO_learning>();
		ResultSet rs2 = null;
		ArrayList<smhVO_learning> re = new ArrayList<smhVO_learning>();

		try {
			getConnection();
			String sql = "select distinct id from learning";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String id1 = rs.getString("id");
				re.add(new smhVO_learning(id1));
			}
			}
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
			rs=null;
		}
		for(int j=0; j<re.size(); j++) {
			System.out.println("======"+re.get(j).getId()+"======");
		try {
			getConnection();
			String sql = "select TO_CHAR(class_day, 'YY-MM-DD') as class_day, count(class_time) from learning where id = ?  group by class_day";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, re.get(j).getId());
			rs = psmt.executeQuery();
			while (rs.next()) {
				String day1 = rs.getString("class_day").split("\\.")[0];
				sql = "select subject, count(class_time) as c from learning where class_day=? group by subject";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, day1);
				rs2 = psmt.executeQuery();
				while (rs2.next()) {
					String a = rs.getString("class_day");
					String b = rs2.getString("subject");
					int c = rs2.getInt("c");
					smhVO_learning abc = new smhVO_learning("","", b, a, c);
					if (rs2.getString("subject").equals("DataBase")) {
						database.add(abc);
					} else if (rs2.getString("subject").equals("PYTHON")) {
						python.add(abc);
					} else if (rs2.getString("subject").equals("JAVA")) {
						java.add(abc);
					} else if (rs2.getString("subject").equals("HTML/CSS")) {
						html.add(abc);
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		System.out.println("DATABASE");
		for (int i = 0; i != database.size(); i++) {
			System.out.println("\t" + database.get(i).getClass_day() + "\t" + database.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + database.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("HTML/CSS");
		for (int i = 0; i != html.size(); i++) {
			System.out.println("\t" + html.get(i).getClass_day() + "\t" + html.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + html.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("JAVA");
		for (int i = 0; i != java.size(); i++) {
			System.out.println("\t" + java.get(i).getClass_day() + "\t" + java.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + java.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
		System.out.println("Python");
		for (int i = 0; i != python.size(); i++) {
			System.out.println("\t" + python.get(i).getClass_day() + "\t" + python.get(i).getClass_time() + "시간");
		}
		System.out.println("\t\t 총 수강 :" + python.stream().mapToInt(i -> i.getClass_time()).sum()+"/20");
	}
	}
}
