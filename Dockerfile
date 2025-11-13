FROM adoptopenjdk/openjdk21:aarch64-alpine


# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо файли з джерела (вашого локального комп'ютера) до контейнера
COPY . /app

# Збираємо проект
RUN mvn clean package

# Команда для запуску .jar файлу
CMD ["java", "-jar", "target/your-app.jar"]
