
cd microservices/product-composite-service; ./gradlew build; cd -; \
cd microservices/product-service; ./gradlew build; cd -; \
cd microservices/recommendation-service; ./gradlew build; cd -; \
cd microservices/review-service; ./gradlew build; cd -;


java -jar microservices/product-composite-service/build/libs/product-composite-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/product-service/build/libs/product-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/recommendation-service/build/libs/recommendation-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/review-service/build/libs/review-service-1.0.0-SNAPSHOT.jar &





curl http://localhost:8000/product-composite/1




kill -9 $(lsof -i tcp:8000)
kill -9 $(lsof -i tcp:8001)
kill -9 $(lsof -i tcp:8002)
kill -9 $(lsof -i tcp:8003)



kill -9 $(lsof -i tcp:3306)
