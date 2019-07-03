### Front Build
- npm install (dependency download)
- npm run dev  (vue test run)
- npm run build (frontend build)

### Jar Build
./gradlew clean -Dspring.profiles.active=local bootjar

### Run SpringBoot Application
cd ~/odyssey/module-service/build/libs
jar -jar ${jarFile Path}

### Use Library
- Vue Daum Map : https://github.com/okchangwon/vue-daum-map

### Login Account
- Member : windsp7 - 1234
- Member : kakao - 4321

### TODO 
1. Vue-Daum-Map 라이브러리 버그
Modal을 이용해 Component 생성시 1회만 정상작동 함 Map 객채에 위,경도 데이터 정상 맵핑 확인 했으나 맵로딩이 일부만 이루어짐 

