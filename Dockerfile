FROM openjdk:17

# Создаем рабочую директорию внутри контейнера
WORKDIR /java

# Копируем JAR-файл из корневой директории в контейнер
COPY Kameleoon-Trial-Task.jar /java/Kameleoon-Trial-Task.jar

# Открываем порт 8001 для внешних подключений
EXPOSE 8001

# Команда, которая будет запущена при старте контейнера
CMD ["java", "-jar", "Kameleoon-Trial-Task.jar"]











