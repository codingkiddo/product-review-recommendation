
cd microservices/product-composite-service; ./gradlew build; cd -; \
cd microservices/product-service; ./gradlew build; cd -; \
cd microservices/recommendation-service; ./gradlew build; cd -; \
cd microservices/review-service; ./gradlew build; cd -;


java -jar microservices/product-composite-service/build/libs/product-composite-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/product-service/build/libs/product-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/recommendation-service/build/libs/recommendation-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/review-service/build/libs/review-service-1.0.0-SNAPSHOT.jar &




cd microservices/product-composite-service; ./gradlew build; cd -;
java -jar -Dspring.profiles.active=docker  microservices/product-composite-service/build/libs/product-composite-service-1.0.0-SNAPSHOT.jar



cd microservices/product-service; ./gradlew build; cd -;
java -jar -Dspring.profiles.active=docker microservices/product-service/build/libs/product-service-1.0.0-SNAPSHOT.jar



cd microservices/recommendation-service; ./gradlew build; cd -;
java -jar -Dspring.profiles.active=docker  microservices/recommendation-service/build/libs/recommendation-service-1.0.0-SNAPSHOT.jar



cd microservices/review-service; ./gradlew build; cd -;
java -jar -Dspring.profiles.active=docker microservices/review-service/build/libs/review-service-1.0.0-SNAPSHOT.jar

curl http://localhost:8000/product-composite/1

curl -X POST http://localhost:8081/product \
	    -H "Content-Type: application/json" --data \
	     '{"productId":123,"name":"product 123","weight":123}'


curl -X POST http://localhost:8080/recommendation \
	      -H "Content-Type: application/json" --data \
	      '{"productId":123,"recommendationId":456,"author":"me","rate":5,"content":"yada, yada, yada"}'

kill -9 $(lsof -i tcp:8000)
kill -9 $(lsof -i tcp:8001)
kill -9 $(lsof -i tcp:8002)
kill -9 $(lsof -i tcp:8003)



kill -9 $(lsof -i tcp:3306)

curl -X POST http://localhost:8080/product-composite -H "Content-Type: application/json" --data \ 
	  '{"productId":12,"name":"product 1","weight":1,"recommendations":[{"recommendationId":1,"author":"author 1","rate":1,"content":"content 1"},{"recommendationId":2,"author":"author 2","rate":2,"content":"content 2"},{"recommendationId":3,"author":"author 3","rate":3,"content":"content 3"}],"reviews":[{"reviewId":1,"author":"author 1","subject":"subject 1","content":"content 1"},{"reviewId":2,"author":"author 2","subject":"subject 2","content":"content 2"},{"reviewId":3,"author":"author 3","subject":"subject 3","content":"content 3"}]}'


'{"productId":1,"name":"product 1","weight":1, "recommendations":[
{"recommendationId":1,"author":"author
1","rate":1,"content":"content 1"},
{"recommendationId":2,"author":"author
2","rate":2,"content":"content 2"},
{"recommendationId":3,"author":"author
3","rate":3,"content":"content 3"}
], "reviews":[
{"reviewId":1,"author":"author 1","subject":"subject
1","content":"content 1"},
{"reviewId":2,"author":"author 2","subject":"subject
2","content":"content 2"},
{"reviewId":3,"author":"author 3","subject":"subject
3","content":"content 3"}
]}'
