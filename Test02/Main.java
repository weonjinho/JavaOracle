package Test02;

public class Main {

	public static void main(String[] args) {
		try {
			//예외 발생할 가능성이 있는 문장.
		} catch (Exception e) {
			//Exception이 발생했을 경우, 이를 처리하기 위한 문장을 적는다.
			//보통 이곳에 예외 메세질글 출력하고 로그로 남김.
			e.printStackTrace(); //예외 메세지를 콘솔화면에 출력한다.
			e.getMessage();//오류에 대한 기본적인 내용을 알수 있지만 상세하지 않다.
		}finally {
			//예외발생여부에 관계없이 항상 수행되어야 하는 문장.
		}
	}
	//try 문에서 Exception 예외가 발생할 경우 catch (Exception e)로 빠져서 그 안의 실행문을 실행한다.
	//마지막의 finally 블럭은 try-catch 문과 함께 예외발생 여부과 관계없이 "항상.무조건"실행된어야할 코드를 적는다.
	//finally는 필수는 아니다.
	
	//try-catch문 플로우
	//-예외가 try 불력에서 발생한 경우.
	//1. 발생한 예외와 일치하는 catch문이 있는지 확인.
	//2. 일치하는 catch문이 있다면 catch불럭 내의 문장을 모두 실행하고 try-catch문을 빠져나가서 그 finally 불럭을 수행.
	//3. 일치하는 catch문이 없다면 예외는 처리되지 못하고 예러 발생.
	
	//-예외가 try 블럭 안에서 발생하지 않은 경우.
	//1. catch 불력을 거치지 않고 전체 try-catch문을 빠져나가서 finally 불럭을 수행한다.
	
	//-예외가 try 블럭 밖에서 발생한 경우.
	//1. 예외는 아무 처리되지 못한 채 에러 발생.

}
