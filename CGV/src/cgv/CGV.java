package cgv;

import javax.swing.JOptionPane;

public class CGV {
	public static void main(String[] args) {
		String helloMsg = "●어서오세요 CGV입니다.●\n";
		String menu = "①예매하기\n②구매하기\n③포인트 조회\n④나가기\n";
		String ageMsg = "[청소년 구매 불가 상품]\n나이를 입력하세요 :\n ";
		String movieList = "①라이언킹(08:00)\n②스파이더맨(12:00)" + "\n③사일런스(23:00) [청소년 관람불가]\n④뒤로가기" + "\n 입력하세요 : ";
		String sideMenu = "[구매하기]\n①팝콘\t2,500원\n②콜라\t1,000원\n③맥주\t2,500원\n④뒤로가기\n입력하세요 :";
		int money = 10;
		int point = 0;
		int choice = 0;
		int age = 0;
		int ticketprice = 10_000;
		int popcorn = 2500;
		int coke = 1000;
		int beer = 2500;
		boolean t_check; // 이런 걸 플레그라고 해~
		/*
		 * 구매하기 1. 팝콘 2. 콜라 3. 맥주 500cc 4. 뒤로가기
		 */
		while (true) {
			t_check = true;
			choice = Integer.parseInt(JOptionPane.showInputDialog(helloMsg + menu));
			if (choice == 4) {
				JOptionPane.showMessageDialog(null, "감사합니다");
				break;
			}
			// 잘못입력했을때 continue
			if (!(choice >= 1 || choice <= 3))
				continue;

			switch (choice) {
			// 예매하기 영역
			case 1:
				if (money - ticketprice < 0) {
					JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					continue;
				}
				// 변수의 재사용
				choice = Integer.parseInt(JOptionPane.showInputDialog(movieList));
				if (choice == 1) {
					JOptionPane.showMessageDialog(null, "라이언킹 예매완료(08:00)");
				} else if (choice == 2) {
					JOptionPane.showMessageDialog(null, "스파이더맨 예매완료(12:00)");
				} else if (choice == 3) {
					age = Integer.parseInt(JOptionPane.showInputDialog(ageMsg));
					if (age > 19) {
						JOptionPane.showMessageDialog(null, "사일런스 예매완료(23:00)");
					} else {
						t_check = false;
						JOptionPane.showMessageDialog(null, "다시 시도해 주세요.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "메인메뉴로 이동합니다");
					continue;
				}
				// 티켓결제영역
				if (t_check) {
					if (point > 0) {
						if (point - ticketprice >= 0) {
							point -= ticketprice;
						} else {
							// ticketprice -=point;
							money -= (ticketprice - point);
							point = 0;
						}
					}
					// 포인트가 0점인 경우
					else {
						money -= ticketprice;
						point += (int) (ticketprice * 0.5);
					}
					JOptionPane.showMessageDialog(null, "현재잔액 : " + money + "원");
				}

				break;
			// 구매하기 영역
			case 2:
				t_check = true;
				choice = Integer.parseInt(JOptionPane.showInputDialog(sideMenu));
				if (!(choice >= 1 || choice < 3)) {
					continue;
				}
				switch (choice) {
				// 팝콘
				case 1:
					if (money - popcorn <= 0) {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
						break;
					} else {
						money -= popcorn;
						JOptionPane.showMessageDialog(null, "팝콘구매완료!");
					}
					break;
				// 콜라
				case 2:
					if (money - popcorn <= 0) {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
						break;
					} else {
						money -= coke;
						JOptionPane.showMessageDialog(null, "콜라구매완료!");
					}
					break;

				// 맥주
				case 3:
					if (money - popcorn <= 0) {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
						break;
					}
					age = Integer.parseInt(JOptionPane.showInputDialog(ageMsg));
					if (age < 19) {
						JOptionPane.showMessageDialog(null, "다시 시도해 주세요.");
					} else {
						money -= beer;
						JOptionPane.showMessageDialog(null, "맥주구매완료!");

					}
					break;
				case 4:
					continue;

				default:
					JOptionPane.showMessageDialog(null, "메인메뉴로 이동합니다");

					continue;
				}
				if (choice == 1 || choice == 2 || choice == 3) {
					JOptionPane.showMessageDialog(null, "현재잔액 : " + money + "원");
					break;// 123번 말고 다른 번호 입력하면 잔액 뜨는 거 막아주기
				}
				// 포인트 조회 영역
			case 3:
				JOptionPane.showMessageDialog(null, "현재 잔여 포인트는" + point + "p 입니다");

				break;

			default:
				break;
			}

		}

	}
}
