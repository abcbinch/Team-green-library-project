# 📗 green-library 도서관 웹사이트
spring boot project

## 소요 기간
### 2024.06 ~ 2024.07 (4주)

## 제작 목적
 도서관의 자료를 확인하고 대출/반납/예약을 할 수 있는 웹사이트입니다.

## 💻 제작자
### 팀장 : 박일규
#### 조원 : 윤솔, 이혜민, 유영현

## 📚 사용 툴
front : 
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white">
<Br>

back :
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">
<br>
ide : <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">
<img src="https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white">
<br>
build : <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
<br>
database : <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
<br>
configuration : <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white">
<br>

## 담당 파트
 - 웹사이트의 전체 UX/UI 디자인
 - 스크롤 인터랙션(스크롤 업 버튼, 아코디언 상단 고정) 기능 제작
 - 아코디언 기능 제작
 - 슬라이드 기능 제작
 - 서브 메뉴 바 제작

## 📋 필수 조건

이 프로젝트를 실행하기 위해서는 다음의 조건이 필요합니다:

1. **Java 17** 이상: Java 언어를 사용하여 개발되었습니다.
2. **Gradle**: 프로젝트 관리 도구로 Gradle을 사용하였습니다.
3. **Spring Boot 3.3.0**: 웹 애플리케이션 개발 프레임워크로 Spring Boot를 사용하였습니다.
4. **Lombok 1.18.32**: 코드를 간결하게 만들기 위해 Lombok을 사용하였습니다.
5. **Redis**: 캐싱 및 세션 관리를 위해 Redis를 사용하였습니다.

## 🚀 프로젝트 실행 방법
1. **프로젝트 클론**: 먼저 이 레포지토리를 로컬 환경에 클론합니다.
2. **빌드**: Gradle을 이용하여 프로젝트를 빌드합니다.
3. **스키마 적용**: `schema.sql` 파일을 실행하여 데이터베이스 스키마를 생성합니다. 이 파일은 데이터베이스의 구조를 정의합니다.
4. **데이터 적용**: `data.sql` 파일을 실행하여 초기 데이터를 데이터베이스에 적용합니다. 이 파일은 데이터베이스에 필요한 초기 데이터를 제공합니다.
