### Front Build
- cd ~/odyssey/module-service/client
- npm install (dependency download)
- npm run dev  (vue test run)
- npm run build (frontend build)

### Jar Build
cd ~/odyssey/module-service

./gradlew clean -Dspring.profiles.active=local bootjar

default url : localhost:8080

최종 빌드 파일 : ${projectRoot}/modular-service.jar  

### Run SpringBoot Application
cd ~/odyssey/module-service/build/libs

java -jar ${jarFile Path}

### Vm option
-Dspring.profiles.active={local | develop | production}
- local : 로컬 개발 시 사용
- develop : dev server 운영 시 사용
- production : real server 운영 시 사용


### Use Library
- Vue Daum Map : https://github.com/okchangwon/vue-daum-map

### Login Account
- Member : windsp7 - 1234
- Member : kakao - 4321

### TODO 
1. Vue-Daum-Map 라이브러리 버그
Modal을 이용해 Component 생성시 1회만 정상작동 함 Map 객채에 위,경도 데이터 정상 맵핑 확인 했으나 맵로딩이 일부만 이루어짐 

