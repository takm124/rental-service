## 의상대여시스템

> 오프라인 매장에서 의상 대여를 위한 웹 서비스 제공

- 종이 전표 관리의 불편함을 줄이기 위한 온라인 전표 시스템 입니다.



## 기술스택

- FrontEnd : Thymeleaf, javascript
- BackEnd : JAVA(11), Spring boot(2.5.3) , H2(개발단계), MariaDB(AWD RDS),  JPA, Gradle
- Server : AWS EC2



## 기능 설명

### 로그인

- 매장 Staff와 매니저만 접근 가능, 지점이 여러개가 있다면 로그인시에 구분되게 만듦



### 메인 페이지

- 당일 기본 전표 통계를 보여줌


![image](https://user-images.githubusercontent.com/72305146/128299749-a88a78ea-abee-4df5-bf80-0f20e544c4c0.png)



### 접수

- 인원을 유동적으로 변경하여 추가시킬 수 있음
- 설문조사 같은경우 다중체크 가능하며 통계용으로 사용됨

![image](https://user-images.githubusercontent.com/72305146/128299775-c0a71414-cd5a-4bee-8b32-88667cd1004d.png)


### 대여

- 접수 완료하게 되면 대여 탭으로 넘어가게 되며 남자, 여자 의상 및 판매 제품을 추가시킬 수 있음

- 대여 완료(결제 완료)하게 되면 결제를 담당한 직원 체크됨
- 전표에 등록된 고객명을 입력하면 해당 전표 검색

![image](https://user-images.githubusercontent.com/72305146/128299802-acf63782-8194-4404-aba6-d071771debfa.png)


### 반납

- 반납완료 또한 담당 직원 체크됨



### 관리자 페이지

- Staff 등록(아이디 패스워드 등 기본 신상정보)

- 전표 조회(시간별, 고객명 기반)


![image](https://user-images.githubusercontent.com/72305146/128299837-77d7ef75-9652-41d8-9410-0cd11dea834e.png)
![image](https://user-images.githubusercontent.com/72305146/128299851-c283fe04-fab1-4cd6-9b57-c6239f93e72d.png)

