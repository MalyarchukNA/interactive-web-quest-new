# interactive-web-quest-new

Web-based text quest built with Java Servlets, JSP and Maven.

## *1. Стек технологий*

```text
1. Java 21
2. Maven 
3. Servlet API 4.0, JSTL 1.2, JSP, HTML4, CSS3
4. Project Lombok
5. JUnit 5 (Jupiter API & Params)
6. SLF4J + Logback Classic 1.5
```

## *2. Функционал*

Приложение представляет собой интерактивный веб-квест, построенный с использованием сервлетов и JSP-страниц.
Принимает решения пользователя, хранит состояния игры, показывает результат в зависимости от выборов игрока.

### Структура и ветвление квеста

> [!IMPORTANT]
> <details>
> <summary><b>👁 Смотреть карту квеста (Осторожно, спойлеры!)</b></summary>
>
> ```mermaid
> graph TD
>     %% --- Начало ---
>     Start((Начало)) --> Reg[Ввод Имени]
>     Reg --> Init[Lucidity=100, Fragments=0]
>     Init --> S1
> 
>     %% --- Слайд 1 ---
>     S1{{"Слайд 1: Холл"}}
>     S1 -->|Выбор А: Лифт| S1_A[L -35]
>     S1 -->|Выбор Б: Лестница| S1_B[F +1]
> 
>     S1_A --> S3
>     S1_B --> S2
> 
>     %% --- Слайд 2 ---
>     S2{{"Слайд 2: Коридор"}}
>     S2 -->|А: Коснуться| S2_A[F +1, L -20]
>     S2 -->|Б: Убежать| S2_B[L +5]
> 
>     S2_A --> S3
>     S2_B --> S3
> 
>     %% --- Слайд 3 ---
>     S3{{"Слайд 3: Номер 104"}}
>     S3 -->|А: Читать| S3_A[F +1, L -30]
>     S3 -->|Б: Искать выход| S3_B[L -10]
> 
>     %% Проверки на Слайде 3
>     S3_B --> Check3{L < 40?}
>     S3_A --> Check3{L < 40?}
>     Check3 -->|Да| Timelessness((Поражение: <БЕЗВРЕМЕНЬЕ>))
>     Check3 -->|Нет| S4
> 
>     %% --- Слайд 4 ---
>     S4{{"Слайд 4: Финал"}}
>     S4 --> FinalChoice{Финальный выбор}
> 
>     FinalChoice -->|А: Ключ| CleanSheet((Победа: СТЕРТАЯ ПАМЯТЬ))
>     FinalChoice -->|Б: Альбом| SF_B[L -40]
>     SF_B --> CheckF{F >= 2 && L >= 30?}
> 
>     %% Проверки финала
>     CheckF -->|Нет| Defeat((Поражение: ПОТЕРЯ РАССУДКА))
>     CheckF -->|Да| TrueFinal((Победа: ИСТИННОЕ Я))
> 
>     %% Стили
>     style TrueFinal fill:#9f9,stroke:#333
>     style CleanSheet fill:#9f9,stroke:#333
>     style Timelessness fill:#f99,stroke:#333
>     style Defeat fill:#f99,stroke:#333
> ```
> </details>


## *3. Инструкция по запуску*

Для запуска проекта потребуется:
```text
1. Java 21 или выше
2. Apache Maven 3.9+
3. Apache Tomcat 9.x
```
### 3.1. Клонирование и сборка артефакта
Склонируйте репозиторий и соберите `.war` файл:

```bash
git clone git@github.com:MalyarchukNA/interactive-web-quest-new.git
cd interactive-web-quest-new
mvn clean package
```

### 3.2. Локальный запуск на Apache Tomcat

#### Вариант А. Ручной деплой
1. Скопируйте полученный файл `interactive-web-quest-new-1.0-SNAPSHOT.war` из папки `target/`.
2. Перенесите его в директорию `webapps` вашего установленного сервера Tomcat.
3. Запустите Tomcat.

#### Вариант Б. Запуск через IntelliJ IDEA
1. Откройте проект в IntelliJ IDEA.
2. Выберите: **Run** -> **Edit Configurations...**
3. Нажмите на **`+`** (Add New Configuration) и выберите **Tomcat Server** -> **Local**.
4. Во вкладке **Server** укажите путь к вашему локальному Tomcat (поле *Application server*).
5. Перейдите во вкладку **Deployment**, нажмите **`+`**, выберите **Artifact...** и укажите `interactive-web-quest-new:war exploded`.
6. В поле **Application context** укажите имя пути, например: `/quest`.
7. Нажмите **Apply** и запустите сервер кнопкой **Run (Shift + F10)**.

### 3.3. Доступ к приложению в браузере

После успешного деплоя откройте браузер и перейдите по адресу:
* Если запускали через **Вариант А**:
  `http://localhost:8080/interactive-web-quest-new-1.0-SNAPSHOT/`
* Если запускали через **Вариант Б**:
  `http://localhost:8080/quest/`

## 4. Тестирование и логирование

### Запуск тестов
Для валидации логики инициализации шагов и проверки `QuestService` запустите JUnit тесты командой:
```bash
mvn test
```

### Как посмотреть логи при ручном запуске:
1. Откройте консоль вашего сервера Tomcat — там отобразятся `INFO` сообщения.
2. Перейдите в рабочую папку Tomcat (или корень вашего проекта в зависимости от способа запуска) и откройте сгенерированный файл `./logs/debug.log` - там записываются `DEBUG` сообщения.