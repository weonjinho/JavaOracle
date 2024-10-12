package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.IdeaDAO;
import dto.IdeaDTO;

public class IdeaService {
		IdeaDAO ideadao = new IdeaDAO();
		public IdeaService(){
			menu();
		}
		private void menu() {//메인메뉴
			Scanner in = new Scanner(System.in);
			boolean flag = true;
			while(flag) {
				System.out.println("1.등록 2.삭제 3.수정 4.전체보기 5.검색 6.종료");
				int selNum = in.nextInt();
				in.nextLine();
				switch (selNum) {
					case 1:ideaAdd();break;
					case 2:ideaDel();break;
					case 3:ideaMod();break;
					case 4:ideaList();break;
					case 5:ideaSearch();break;
					case 6:flag = false;break;
				}
			}
			in.close();
		}
		private void ideaAdd() {//정보 추가
			//필요한 메소드 : insert();
			//첫 변째
			System.out.println("신규 idea 등록하세요.");
			Scanner in = new Scanner(System.in);
			System.out.println("제목입력하세요.");
			String title = in.nextLine();
			System.out.println("내용입력하세요.");
			String content = in.nextLine();
			System.out.println("작성자 입력하세요.");
			String writer = in.nextLine();
			IdeaDTO ideadto = new IdeaDTO();
			ideadto.setTitle(title);
			ideadto.setContent(content);
			ideadto.setWriter(writer);
			ideadao.insert(ideadto);
		}
		
		
		private void ideaDel() {//정보 삭제
			//필요한 메소드 : delete();
			Scanner in = new Scanner(System.in);
			System.out.println("--- 전체 아이디어 리스트 ---");
			ideaList();
			System.out.println("삭제할 아이디어의 번호를 입력하세요.");
			int del = in.nextInt();
			in.nextLine();
			IdeaDTO ideaDto = new IdeaDTO();
			ideaDto.setNum(del);
			ideadao.delete(ideaDto);
		}
		
		
		private void ideaMod() {//정보 수정
			//필요한 메소드 : selectOne(), modify();
			//selectOne() 의 기능 : 1개 튜플의 정보 가져오기.
			//modify()의 기능 : 선택한 튜플 update하기.
			
			//로직 : 
			//1.수정할 정보를 검색하고 가져온다. ( "번호"로 검색 )
			//2.검색한 정보를 수정한다.
			Scanner in = new Scanner(System.in);
			System.out.println("--- 전체 아이디어 리스트 ---");
			ideaList();
			System.out.println("수정할 아이디어의 번호를 입력하세요.");
			int mod = in.nextInt();
			in.nextLine();
			IdeaDTO modDto = ideadao.selectOne(mod);
			System.out.println("선택한 튜플의 정보");
			System.out.println(modDto.toString());
			System.out.println("제목 수정하세요.");
			String title = in.nextLine();
			modDto.setTitle(title);
			System.out.println("내용 수정하세요.");
			String content = in.nextLine();
			modDto.setContent(content);
			//수정
			ideadao.modify(modDto);
		}
		
		
		private void ideaList() {//전체보기
			//필요한 메소드 : selectAll();
			//selectAll()의 기능 : 모든 튜플속 정보가져오기.
			
			//DB속 모든 튜플의 값을 ArrayList로 가져와서, 향상된 for문으로 출력한다.
			//selectAll()로 DAO에 접근한다.
			//DAO에서 필요한 데이터를 가져온다.
			ArrayList<IdeaDTO> ideaList = ideadao.selectAll();
			for(IdeaDTO i : ideaList) {
				System.out.println(i.toString());
				System.out.println();
			}
		}
		 
		private void ideaSearch() { //정보 검색
			//필요한 메소드 : 
			//"제목"으로 검색
			Scanner in = new Scanner(System.in);
			ideaList();
			System.out.println("검색할 정보의 제목을 입력하세요.");
			String search = in.nextLine();
			ArrayList<IdeaDTO> ideaList = ideadao.select(search);
			for(IdeaDTO i : ideaList) {
				System.out.println(i.toString());
			}
		}
}
