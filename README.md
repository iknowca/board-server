# iknow-Board-server
프로젝트의 게시판 서비스를 담당하는 서버입니다.

REST API를 제공하며, 클라이언트는 이 서버를 통해 게시판 서비스를 사용할 수 있습니다.<br>
프론트엔드는 포함되어 있지 않으며 별도 [레포지토리](https://github.com/iknowca/iknow-main-frontend)에서 구현하였습니다.
## 서버 구조
![img.png](readme/server-arch.png)

## 서버 구현 기능
- **문서화**<br>
  Notion을 사용하여 [API 명세서](https://colorful-chimpanzee-429.notion.site/API-Docs-77854fa65f354cbf99ae166328860882?pvs=4)를 작성하였습니다.<br>
  Mermaid를 사용하여 기능의 흐름을 Sequence Diagram, Flow Chart로 작성하였습니다.


- **테스트**<br>
    1. 기능 테스트<br>
       Junit5, Mockito를 사용하여 테스트 코드를 작성하였습니다.<br>
       초기에는 test double을 사용한 고립 테스트로 시작하였지만,<br>
       잦은 API설계 변경에 대응하기 위해 production object를 사용한 통합 테스트로 변경하였습니다.<br><br>
    2. 부하 테스트<br>
       nGrinder와 POSTMAN을 사용하여 부하 테스트를 수행하였습니다.


- **배포 자동화**<br>
  Github Actions를 사용하여 CI/CD를 구축하였습니다.<br>
  Main 브랜치에 push가 되면 testcontainer를 사용해 테스트를 수행하고, 테스트가 성공하면 빌드를 수행합니다.<br>
  빌드가 성공하면 SCP 액션을 통해 서버로 빌드파일을 복사하고<br>서버에서 복사된 빌드 파일을 Docker 이미지로 제작하여 container로 실행시킵니다.<br>
  <br>

- **Gateway**<br>
  nginx를 사용하여 API Gateway를 구축하였습니다.<br>


## 프로젝트 아키텍쳐
![img.png](readme/project-arch.png)
[auth-server](https://github.com/iknowca/iknow-authentication-server)는 프로젝트의 인증과 인가를 담당하는 서버입니다.<br>
[board-server](https://github.com/iknowca/board-server)는 게시글을 담당하는 서버입니다.<br>
[frontend](https://github.com/iknowca/see-space-frontend)는 프론트엔드입니다.<br>

## 기술 스택
- Spring Boot / Security / MyBatis
- DB: MySQL/ Redis
- Test: Junit5/ Mockito/ Testcontainer
- CI/CD: Github Actions
- Container: Docker
- Gateway: Nginx
- Documentation: Notion, Mermaid
- performence test: nGrinder, POSTMAN
- OS: Ubuntu, MacOS
- IDE: IntelliJ, VSCode
- VCS: Git

## 프로젝트 실행 방법
1. Docker 설치
2. Docker-compose 설치
3. 프로젝트 루트 디렉토리에서 `docker-compose up` 명령어 실행
4. `localhost:9001`으로 접속하여 서비스 확인
 
