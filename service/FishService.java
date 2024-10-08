package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.FishDAO;
import dto.FishDTO;

public class FishService {
//	ArrayList<FishDAO> fishDao = new ArrayList<>();
	FishDAO fishDao = new FishDAO();
	public void menu() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("1.등록");
			System.out.println("2.삭제");
			System.out.println("3.수정");
			System.out.println("4.전체보기");
			System.out.println("5.검색");
			System.out.println("6.종료");
			System.out.println("선택 > ");
			int num = in.nextInt();
			in.nextLine();
			if(num == 1) {
				add();
			}else if(num == 2) {
				delete();
			}else if(num == 2) {
				update();
			}else if(num == 2) {
				list();
			}else if(num == 2) {
				search();
			}else {
				break;
			}
		}
	}

	private void add() {
		//등록할 값을 입력 받아서 DAO에게 넘긴다.
		Scanner in = new Scanner(System.in);
		FishDTO f = new FishDTO();
		System.out.println("등록할 id를 입력하세요.");
		String id = in.nextLine();
		System.out.println("등록할 비밀번호를 입력하세요.");
		String pwd = in.nextLine();
		f.setId(id);
		f.setPwd(pwd);
		fishDao.add(f);
	}

	private void delete() {
		//삭제할 값을 입력 받아서 DAO에게 넘긴다.
		Scanner in = new Scanner(System.in);
		FishDTO f = new FishDTO();
		String delete = in.nextLine();
		fishDao.delete(f);
	}

	private void update() {
		//1. 수정할 id를 입력받는다.
		//2. 해당 id가 위치한 튜플을 찾는다.
		//3. 해당 튜플에 정보를 다 가져온다.
		Scanner in = new Scanner(System.in);
		FishDTO f = new FishDTO();
		System.out.println("수정할 id를 입려하세요.");
		String id = in.nextLine();
		f.setId(id);
		fishDao.findId(f);
		
	}

	private void list() {
		
	}

	private void search() {
		
	}
}
