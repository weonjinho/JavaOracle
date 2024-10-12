package main;

import service.IdeaService;

public class Main {

	public static void main(String[] args) {
		// 코드 동작 흐름 : 
		// Main --> IdeaService --> DTO --> DAO --> DB
		// 코드 작성 순서 : 
		// Main --> DB 테이블 생성 --> DTO
		new IdeaService();
	}

}
