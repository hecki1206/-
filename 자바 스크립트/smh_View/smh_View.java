package mini_project;

import java.awt.Checkbox;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class smh_View {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		smhDAO smhDAO = new smhDAO();
		Date in;
		Date out;
		boolean find = false;

		System.out.println("\t            ▄▄▄▄▄▄▄▄▄▄          \r\n" + "\t         ▄▀▀▀███████████▄▄      \r\n"
				+ "\t               ▀▀█████████▄▄    \r\n" + "\t         ▄██▄▄    ▀▀████████▄▄  \r\n"
				+ "\t    ▄█▄   ▀████▄    ██████████▄ \r\n" + "\t    ████▄   ▀█████▄█████▀▀▀▀███ \r\n"
				+ "\t    ▀██████▄   ▀██████       ▀█ \r\n" + "\t      ▀██████▄   ████   ▄██▄    \r\n"
				+ "\t        ▀▀████   ███  ▄██▀███   \r\n" + "\t    ██▄         ███   ██   ██   \r\n"
				+ "\t    ▀████▄▄   ▄██▀   ██  ▄██    \r\n" + "\t     ▀██████████▀   █▀  ▄██     \r\n"
				+ "\t       ▀▀█████████▄███▄▄██      \r\n" + "\t           ▀▀█████████▀▀        \r\n"
				+ "\t                                \r\n" + "");

		System.out.println("\t ========= 스마트인재개발원 ===========");
		while (true) {
			System.out.println("");
			System.out.print("[1] 회원가입 [2] 로그인 [3] 종료 >> ");
			int input = sc.nextInt();
			if (input == 1) {
				// 학생 또는 관리자 회원가입
				System.out.println("-----회원가입-----");
				System.out.print("사용자의 ID를 입력하세요 >> ");
				String id = sc.next();
				System.out.print("사용자의 PW를 입력하세요 >> ");
				String pw = sc.next();
				System.out.print("사용자의 이름을 입력하세요 >> ");
				String name = sc.next();
				System.out.print("사용자의 전화번호를 입력하세요 >> ");
				String phone = sc.next();
				System.out.print("사용자의 회원구분(0, 1)를 입력하세요 >> ");
				String admin = sc.next();
				smhVO_signup smhVO_signup = new smhVO_signup(id, pw, name, phone, admin);
				int result = smhDAO.insert(smhVO_signup);

				if (result > 0) {
					System.out.println("회원 가입 완료!!");
				} else {
					System.out.println("회원 가입 실패@@");
				}

				// 학생 또는 관리자 로그인
			} else if (input == 2) {
				System.out.println("-----로그인-----");
				System.out.print("로그인 할 ID를 입력하세요 >> ");
				String id = sc.next();
				System.out.print("로그인 할 PW를 입력하세요 >> ");
				String pw = sc.next();

				smhVO_signup smhVO_signup = new smhVO_signup(id, pw);
				int result = smhDAO.login(smhVO_signup);
				ArrayList<smhVO_signup> re = smhDAO.info();
			
				// 해당하는 아이디나 비밀번호가 일치하는지 검사하는 단계
				for(int i=0; i < re.size(); i++) { //ArrayList<smhVO_signup> info() 이 부분 result1 설정한 거 확인하기
					smhVO_signup smhSearch = re.get(i);
					if(smhSearch.getId().equals(id) && smhSearch.getPw().equals(pw)) {
						find = true;
						break;
					}
					
				}
				if(!find) {
					System.out.println("입력하신 아이디나 비밀번호를 잘못 입력했습니다.");
				}
				

				if (result == 1) { // result = 1 학생
					System.out.println("학생 로그인 완료!!");
					System.out.println("");
					System.out.print("[1] 수강확인 [2] 회원탈퇴 [3] 회원정보변경 [4] 출결확인 [5]종료 [6] 퇴실하기 >> ");
					input = sc.nextInt();
					if (input == 1) {
						System.out.println("===== 수강확인 화면으로 접속했습니다 =====");
						in = new Date(System.currentTimeMillis());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						System.out.println(df.format(in.getTime()));
						String day = df.format(in.getTime()).substring(0, 10);
						String day2 = df.format(in.getTime()).substring(11, 13);
						String x = smhDAO.current_time(day2);
						smhVO_learning abc = smhDAO.subj(day, x);
						if (smhDAO.check(smhVO_signup, day)) {
							smhDAO.xin(smhVO_signup, abc, day, x);
						}

					} else if (input == 2) {
						System.out.println("===== 회원탈퇴 화면으로 접속했습니다 =====");
						System.out.print("탈퇴할 ID를 입력해주세요 >> ");
						id = sc.next();
						System.out.print("PW를 입력를 입력해주세요 >> ");
						pw = sc.next();
						
						result = smhDAO.delete(smhVO_signup);
						ArrayList<smhVO_signup> re1 = smhDAO.info();
						for(int i=0; i < re1.size(); i++) { //ArrayList<smhVO_signup> info() 이 부분 result1 설정한 거 확인하기
							smhVO_signup smhSearch = re1.get(i);
							if(smhSearch.getId().equals(id) && smhSearch.getPw().equals(pw)) {
								find = true;
								break;
							}
							
						}
						if(!find) {
							System.out.println("입력하신 아이디나 비밀번호를 잘못 입력했습니다.");
						}
						

						if (result > 0) {
							System.out.println("회원탈퇴완료!!");
						} else {
							System.out.println("회원탈퇴실패@@");
						}
					} else if (input == 3) {
						System.out.println("-----회원정보변경-----");
						System.out.print("변경할 PW >> ");
						pw = sc.next();
						System.out.print("변경할 이름 >> ");
						String name = sc.next();
						System.out.print("변경할 전화번호 >> ");
						String phone = sc.next();
						System.out.print("변경할 회원의 아이디 >> ");
						id = sc.next();
						smhVO_signup smhVO_signup1 = new smhVO_signup(id, pw, name, phone);
						result = smhDAO.update(smhVO_signup1);

						if (result > 0) {
							System.out.println("회원 정보 변경 완료!!");
						} else {
							System.out.println("회원 정보 변경 실패@@");
						}

					}
					else if (input == 4) {
						System.out.println("===== 출결내역을 확인합니다 =====");
						in = new Date(System.currentTimeMillis());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String day = df.format(in.getTime()).substring(0, 10);
						smhDAO.CC(smhVO_signup,day);
						
					}
					else if (input == 5) {
						System.out.println("===== 로그아웃을 시도합니다 =====");
						break;

					} else if (input == 6) {
						System.out.println("===== 퇴실을 시도합니다 =====");
						in = new Date(System.currentTimeMillis());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						System.out.println(df.format(in.getTime()));
						String day = df.format(in.getTime()).substring(0, 10);
						String day2 = df.format(in.getTime()).substring(11, 13);
						String y = smhDAO.current_time(day2);
						String x = smhDAO.findX(smhVO_signup, day);
						smhDAO.CTX(smhVO_signup, x, y, day);
						break;
					} 

				} else if (result == 2) { // result = 2 관리자
					System.out.println("관리자 로그인 완료!!");
					System.out.println("");
					System.out.print("[1] 전체 학생조회 [2] 관리자탈퇴 [3] 관리자 정보 변경 [4] 종료 >> ");
					input = sc.nextInt();
					if(input == 1) {
						System.out.println("----전체 학생 수강내역을 조회합니다 ----\n");
						in = new Date(System.currentTimeMillis());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String day = df.format(in.getTime()).substring(0, 10);
						smhDAO.All_student(day);
						
						
					}
					else if(input == 2) {
						System.out.println("----관리자 탈퇴를 시도합니다 ----");
						System.out.print("탈퇴할 ID를 입력해주세요 >> ");
						id = sc.next();
						System.out.print("PW를 입력를 입력해주세요 >> ");
						pw = sc.next();
						for(int i=0; i < re.size(); i++) { //ArrayList<smhVO_signup> info() 이 부분 result1 설정한 거 확인하기
							smhVO_signup smhSearch = re.get(i);
							if(smhSearch.getId().equals(id) && smhSearch.getPw().equals(pw)) {
								find = true;
								break;
							}
							
						}
						if(!find) {
							System.out.println("입력하신 아이디나 비밀번호를 잘못 입력했습니다.");
						}

						if (result > 0) {
							result = smhDAO.delete(smhVO_signup);
							System.out.println("회원탈퇴완료!!");
						} else {
							System.out.println("회원탈퇴실패@@");
						}
					}
					else if(input == 3) {
						System.out.println("----관리자 정보 변경을 시도합니다 ----");
						System.out.print("변경할 PW >> ");
						pw = sc.next();
						System.out.print("변경할 이름 >> ");
						String name = sc.next();
						System.out.print("변경할 전화번호 >> ");
						String phone = sc.next();
						System.out.print("변경할 회원의 아이디 >> ");
						id = sc.next();
						smhVO_signup smhVO_signup1 = new smhVO_signup(id, pw, name, phone);
						result = smhDAO.update(smhVO_signup1);

						if (result > 0) {
							System.out.println("회원 정보 변경 완료!!");
						} else {
							System.out.println("회원 정보 변경 실패@@");
						}
					}
					else if(input == 4) {
						System.out.println(" 프로그램을 종료합니다.");
						break;
					}

			} 
			} else if (input == 3) {
				System.out.println(" 프로그램을 종료합니다.");
				break;
			}
			else {
				System.out.println("번호를 잘못 입력하였습니다.");
			}
		}
	}
}
