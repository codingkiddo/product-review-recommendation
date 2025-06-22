./gradlew microservices:product-service:test --tests PersistenceTests


./gradlew build && docker-compose build && docker-compose up


Try to kill all the processes using the port 3306:
sudo kill `sudo lsof -t -i:3306`