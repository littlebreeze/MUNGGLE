
<img src="/assets/logo.png"  width="400px" height="200px">

# 🐶 멍글멍글
> 🐾참쉽조 (서울 4반 10조)
>
> 사람들이 반려견의 일생을 기록하고 공유하며 즐거움을 나눌 수 있는 반려견 SNS
>
> 프로젝트 기간 : 2024.01.03 - 2024.02.16 (7주)

## 🤝 팀원 소개

### 김태현 (프론트엔드, 팀장)
### 김평섭 (프론트엔드)
### 배정식 (프론트엔드, 테크리더)
### 이윤지 (백엔드)
### 이지원 (백엔드)
### 이형우 (백엔드, 테크리더)


## 💻 기술 스택

### FrontEnd & BackEnd

[![React Native](https://img.shields.io/badge/React_Native-0.73.2-blue?style=flat&logo=react)](https://reactnative.dev/)
[![Kakao Map API](https://img.shields.io/badge/API-Kakao_Map-red?style=flat)](https://developers.kakao.com/docs/latest/ko/local/dev-guide)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.1-green?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![MariaDB](https://img.shields.io/badge/MariaDB-10.11.6-yellow?style=flat&logo=mariadb)](https://mariadb.org/)
[![Redis](https://img.shields.io/badge/Redis-10.11.6-red?style=flat&logo=redis)](https://redis.io/)
[![WebSocket](https://img.shields.io/badge/WebSocket-gray?style=flat&logo=websocket)](https://en.wikipedia.org/wiki/WebSocket)

### Cooperation

[![Git](https://img.shields.io/badge/Git-gray?style=flat&logo=git)](https://git-scm.com/)
[![GitLab](https://img.shields.io/badge/GitLab-gray?style=flat&logo=gitlab)](https://about.gitlab.com/)
[![Notion](https://img.shields.io/badge/Notion-gray?style=flat&logo=notion)](https://www.notion.so/)
[![Jira](https://img.shields.io/badge/Jira-gray?style=flat&logo=jira)](https://www.atlassian.com/software/jira)
[![Mattermost](https://img.shields.io/badge/Mattermost-gray?style=flat&logo=mattermost)](https://mattermost.com/)

### Tools

[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-2023.1.3-red?style=flat&logo=intellij-idea)](https://www.jetbrains.com/idea/)
[![Android Studio](https://img.shields.io/badge/Android_Studio-2022.3.1.21-green?style=flat&logo=android-studio)](https://developer.android.com/studio)

### Infra

[![AWS EC2](https://img.shields.io/badge/AWS_EC2-20.04.6-orange?style=flat&logo=amazon-aws)](https://aws.amazon.com/ec2/)
[![Docker](https://img.shields.io/badge/Docker-24.0.7-blue?style=flat&logo=docker)](https://www.docker.com/)
[![AWS S3](https://img.shields.io/badge/AWS_S3-24.24.24-orange?style=flat&logo=amazon-s3)](https://aws.amazon.com/s3/)
[![Ubuntu](https://img.shields.io/badge/Ubuntu-20.04.06-purple?style=flat&logo=ubuntu)](https://ubuntu.com/)
  
## 🏛️ 아키텍처

![구조](/assets/구조.png)

## 📊 ERD 다이어그램

![ERD](/assets/ERD.png)

## 🎨 와이어프레임
<img src="/assets/와이어프레임.png"  width="400px" height="600px">

## 🐕 주요 기능
캡쳐나 gif 붙히기

## ⚙️ 전체 기능 소개

|  |기능|기능 설명|
|----|--------|------------|
|1|access token, refresh token|각 요청에서 Access token과 Refresh token을 통해 Access Token을 재발급 받는다.|
|2|소셜 연동 회원가입|소셜 로그인 기능을 활용한 회원가입을 한다.|
|3|이메일 인증|이메일 인증을 통해 로그인 아이디를 설정한다.|
|4|사용자 닉네임 중복체크|서비스 내에서 사용할 닉네임을 설정한다.|
|5|비밀번호 입력|로그인할때 사용할 비밀번호를 사용한다.|
|6|로그인 기능|아이디와 비밀번호를 입력해 로그인한다.|
|7|소셜 로그인|네이버/카카오/구글 로그인 정보를 통해 소셜 로그인한다.|
|8|프로필 수정|사용자 닉네임을 변경한다.|
|9|배경사진 수정|사용자의 배경사진을 변경한다.|
|10|배경사진 삭제|사용자의 배경사진을 삭제한다.|
|11|프로필사진 삭제|사용자의 프로필사진을 삭제한다.|
|12|프로필 수정|프로필에 기재될 소개글, 비밀번호를 변경한다.|
|13|아이디 찾기|회원의 아이디를 찾는다.|
|14|비밀번호 찾기|회원의 비밀번호를 찾는다.|
|15|비밀번호 변경|회원의 비밀번호를 변경한다.|
|16|마이페이지|마이페이지에 들어간다.|
|17|프로필 검색|회원 닉네임을 통해 검색 키워드가 포함된 회원 프로필 목록을 보여준다.|
|18|회원프로필상세|회원 프로필에 들어가 배경 사진, 프로필 사진 프로필 내용, 팔로잉, 팔로워 수, 목록 해당 회원이 작성한 글에 접근 가능하다.|
|19|팔로워 목록 확인|팔로워 목록을 확인한다.|
|20|팔로잉 목록 확인|팔로잉 목록을 확인한다.|
|21|팔로워 수 확인|회원의 팔로워 수를 확인하다.|
|22|팔로잉 수 확인|회원의 팔로잉 수를 확인한다.|
|23|팔로잉|회원을 팔로잉한다.|
|24|언팔로우|회원을 언팔로우한다.|
|25|팔로워 삭제|팔로워를 삭제한다.|
|26|차단하기|회원을 차단한다.|
|27|차단 해제|차단한 회원을 차단 해제한다.|
|28|차단 목록|차단한 회원의 목록을 본다.|
|29|회원 탈퇴|회원이 더이상 서비스를 이용하지 않으면 탈퇴할 수 있다.|
|30|반려견 등록|사용자와 함께하고 있는 반려견을 등록한다.|
|31|품종 검색|반려견의 품종을 검색한다.|
|32|반려견 수정|반려견 정보를 수정한다.|
|33|반려견 목록|나와 함께하는 반려견 목록을 확인할 수 있다.|
|34|반려견 상세|나와 함께하는 반려견의 세부 정보를 확인할 수 있다.|
|35|반려견 삭제|반려견의 정보를 삭제한다.|
|36|게시물 등록|사진이나 영상을 등록한다.|
|37|게시물 수정|사진이나 영상을 수정한다.|
|38|게시물 삭제|사진이나 영상을 삭제한다.|
|39|게시물 검색|게시물을 제목으로 검색한다.|
|40|게시물 상세보기|게시물에 관한 모달을 생성한다.|
|41|게시물 태그 검색|태그가 포함된 게시글을 검색할 수 있다.|
|42|게시물 사진 모아보기|프로필 페이지에서 해당일(월)에 올린 사진들을 모아볼 수 있다. (캘린더 형식)|
|43|마이페이지|유저의 게시글/스크랩/반려견 목록을 확인할 수 있다.|
|44|큐레이팅 게시물 목록|메인 페이지에서 사용자별 큐레이팅 기준에 맞는 게시글 목록을 볼 수 있다.|
|45|팔로우 유저 게시물 목록|팔로우한 유저의 게시물 목록을 최신순으로 전달|
|46|게시물 좋아요|게시물에 좋아요 표시/해체를 할 수 있다.|
|47|게시물 스크랩|게시물을 스크랩 추가/해제를 할 수 있다.|
|48|댓글 등록|게시물에 댓글을 작성한다.|
|49|댓글 수정|게시물에 댓글을 수정한다.|
|50|댓글 삭제|게시물에 댓글을 삭제한다.|
|51|댓글 목록|게시물에 해당하는 댓글 목록을 볼 수 있다.|
|52|댓글 상세|특정 게시글에 특정 댓글 상세를 볼 수 있다.|
|53|댓글 좋아요|댓글에 좋아요 표시/해제할 수 있다.|
|54|산책 등록|사용자가 산책한 경로와 리뷰를 기록한다.|
|55|산책 수정|등록했던 산책 리뷰를 수정할 수 있다.|
|56|내 산책 모아보기|캘린더 형식으로 산책 기록을 확인할 수 있다.|
|57|산책 상세|산책 세부 기록을 확인할 수 있다.|
|58|산책 삭제/공개 변경|산책 기록 공개 여부를 변경한다.|
|59|내 위치 산책 보기|산책 경로가 고민될 때, 산책 페이지에서 다른 사람의 산책로를 확인할 수 있다.|
|60|매칭 특징 추가|매칭을 위한 내 반려견 특징을 수정한다.|
|61|상대 특징 추가|매칭을 원하는 반려견 특징을 생성한다.|
|62|특징 수정|반려견 특징을 수정한다.|
|63|매칭 결과 보여주기|원하는 특징 목록에 하나라도 포함되는 반려견 리스트를 보여준다.|
|64|내/상대 특징 보기|본인이 입력한 내 반려견 특징, 매칭을 원하는 반려견 특징을 확인한다.|
|65|옵션 온오프|본인의 반려견이 매칭신청을 받기을 원하는지 여부를 전환한다.|
|66|매칭 결과 전송|매칭의 결과를 전송한다.|
|67|SNS 푸시 알림|특정 이벤트가 일어났을 때 알림을 받는다.|
|68|대화방 생성|대화할 상대를 선택한 후, 대화방을 생성한다.|
|69|메세지 전송|대화방내의 상대에게 메세지를 전송한다.|
|70|메세지 삭제|대화방내의 상대에게 보냈던 메세지를 삭제한다.|
---

## 📦 포팅 메뉴얼



