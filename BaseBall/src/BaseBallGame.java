import java.util.Random;
import java.util.Scanner;

class BaseBallGame {
	
	Random r = new Random();
	Scanner sc = new Scanner(System.in);
	int[] com = new int[4];
	int[] user = new int[4];
	int gameCount = 0;
	int pullGame = 10;
	int quizCount = 0; //문제를 몇번만에 풀었나를 확인해서 평균을 내기위한 멤버변수.
	int strike = 0;
	int ball = 0;

	void GameStart(){
		while(true){//for문으로 바껴서 총횟수와 비교해서 넘어가지만 않게 하면 될것같고...
//		for(int i=1; i<=pullGame; i++) { //여기다 하면 안댐!
			System.out.println("게임을 시작합니다.");
			gameCount++;
			System.out.println("현재 게임 횟수 : " + gameCount+"회 / " + "총게임수 : " + pullGame );
			System.out.println("-------------------------------------------------------------------------------------");
			while(true){
				com[0] = r.nextInt(9)+1;
				com[1] = r.nextInt(9)+1;
				com[2] = r.nextInt(9)+1;
				com[3] = r.nextInt(9)+1;
				// 중복되는 숫자가 있으면 안되므로 중복체크를 해줘야하는 로직 필요 / 이것도 좀더 간단한방법이 없을까 좀더 고민해보자.
				if(!(com[0]==com[1] || com[0]==com[2] || com[1]==com[2] || com[1]==com[3] || com[2]==com[3] || com[3]==com[0])){ 
					break;
				}
			}
			System.out.println("숫자가 생성되었습니다 : " + com[0] + com[1] + com[2] + com[3]); // 생성된 숫자 확인 및 무한루프를 도는지 확인 
			for(int i=1; i<=pullGame; i++) { //유저가 플레이하는 로직은 이쪽이기에 이쪽의 while문을 for문으로 변경
				strike = 0; // 새로운 게임이 시작될때마다 초기화
				ball = 0;
				System.out.println("첫번째 : 1~9 숫자중 원하는 숫자를 하나만 입력하신 후에 엔터를 눌러주세요");
				user[0] = sc.nextInt();
				System.out.println("두번쨰 : 1~9 숫자중 원하는 숫자를 하나만 입력하신 후에 엔터를 눌러주세요");
				user[1] = sc.nextInt();
				System.out.println("세번째 : 1~9 숫자중 원하는 숫자를 하나만 입력하신 후에 엔터를 눌러주세요");
				user[2] = sc.nextInt();
				System.out.println("네번쨰 : 1~9 숫자중 원하는 숫자를 하나만 입력하신 후에 엔터를 눌러주세요");
				user[3] = sc.nextInt();
				
				// 이부분을 좀더 간단하게 줄이고 싶은데 피드백을 한번 받아보고싶다.
				if(user[0]==com[0]) {
					strike++;
				}else if(user[0]==com[1] || user[0]==com[2] || user[0]==com[3]) {
					ball++;
				}
				if(user[1]==com[1]) {
					strike++;
				}else if(user[1]==com[0] || user[1]==com[2] || user[1]==com[3]) {
					ball++;
				}
				if(user[2]==com[2]) {
					strike++;
				}else if(user[2]==com[0] || user[2]==com[1] || user[2]==com[3]) {
					ball++;
				}
				if(user[3]==com[3]) {
					strike++;
				}else if(user[3]==com[0] || user[3]==com[1] || user[3]==com[2]) {
					ball++;
				}
				// 여기까지 로직을 좀더 간단하게 해보고싶다.
				if(strike==4) {
					System.out.println("스트라이크 4개로 사용자가 승리하셧습니다.");
					quizCount++;
					break; // 반복문 밖으로 이동한다.
				}else {
					if(strike==0 && ball==0) {
						System.out.println("숫자가 전부 다르므로 아웃입니다.");
						quizCount++;
					}else {
						System.out.println(strike+"스트라이크" + ball + "볼");
						quizCount++;
						continue; //다시 숫자 입력으로 보낸다.
					}
				}
			}
			// 위에 와일문을 포문으로 바꿀꺼임 총 경기는 10번으로 하되 중간에 멈출수 있도록 선택지를 만들어 줘야된다.
			if (gameCount == 10) { //게임의 카운트 횟수가 10이 되면 게임 종료
				System.out.println("10번의 게임횟수를 모두 소모하셧습니다.");
				int avg = quizCount/gameCount;
				System.out.println("문제 풀이에 대한 평균횟수는 : " + avg + "회 입니다.");
				System.exit(0);
			}else {// 그게아니라면 선택지를 줄 수 있게 밑의 로직실행
			}
			System.out.println("게임을 재시작 하시려면 0번을, 그만하고싶으시면 1번을 입력 후 엔터를 눌러주세요");
			int reGame = sc.nextInt(); // 0은 재시작, 1은 게임종료
			if(reGame==0) { //재시작시 처음으로 돌아가서 gameCount +1
				System.out.println("게임을 재시작합니다.");
				System.out.println("-------------------------------------");
				continue;
			}else { // 종료시 바로 게임 종료
				System.out.println("게임을 종료합니다.");
				int avg = quizCount/gameCount;
//				System.out.println("횟수 : "+avg);
				System.out.println("총 플레이한 게임수는 : " + gameCount + "회 이며, 문제 풀이에 대한 평균횟수는 : " + avg + "회 입니다.");
				System.out.println("");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) 
	{
		BaseBallGame bg = new BaseBallGame();
		bg.GameStart();
	}
}