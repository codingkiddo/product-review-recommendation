java -jar microservices/product-composite-service/build/libs/product-composite-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/product-service/build/libs/product-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/recommendation-service/build/libs/recommendation-service-1.0.0-SNAPSHOT.jar &
java -jar microservices/review-service/build/libs/review-service-1.0.0-SNAPSHOT.jar &

curl http://localhost:8000/product-composite/1

kill -9 $(lsof -i tcp:8000)
kill -9 $(lsof -i tcp:8001)
kill -9 $(lsof -i tcp:8002)
kill -9 $(lsof -i tcp:8003)


----------------------------------------------------------------------------------------------------------------


echo 'Runtime.getRuntime().availableProcessors()' | sudo docker run --rm -i eclipse-temurin:17 jshell -q

sudo docker run -it --rm eclipse-temurin:17 java -XX:+PrintFlagsFinal | grep "size_t MaxHeapSize"

sudo docker run -it --rm -m=1024M eclipse-temurin:17 java -Xmx600m -XX:+PrintFlagsFinal -version | grep "size_t MaxHeapSize"




docker run --rm -p8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" --name product-service-1  product-service
docker logs product-service-1 -f
docker rm -f product-service-1
