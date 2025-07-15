# Online Quiz Application (Java + javaFx + JDBC)

##  Project Overview
An interactive desktop-based Online Quiz System where users can:
- Register/Login
- Take multiple-choice quizzes
- Track their scores
- View leaderboard rankings (optional)
- Admins can create/manage quizzes and questions

---

##  Features Implemented
- User Authentication (Login/Register)
- Admin quiz & question management
- Quiz-taking interface with score feedback
- Result tracking and history view
- Leaderboard showing top scorers
- Error handling and user input validation
- Password security using hashing

---

##  Technologies Used
- JavaFx  For graphical user interface
- JDBC for Database access
- MySQL as the database
- MVC Architecture

---

##  How to Run
1. Setup the database using `quiz.sql`.
2. Edit `DBConnection.java` with your database credentials.
3. Compile the project using any Java IDE or terminal.
4. Run the application via `Main.java` or the `Quiz.jar`.

---
Note:
A separate service layer (business logic layer) is not included in this project to keep it lightweight and focused for academic/internship-level requirements.  
In real-world or large-scale applications, a service layer helps separate UI from business logic and improves maintainability and testability.

---

## 🚀 How to Run the Project

### 1 Setup Database

- Import the SQL file using MySQL command line:

```bash
mysql -u root -p < quizapp_dump.sql
```

Or create the database manually and import the dump.

---

### 2 Edit DB Credentials

Open `src/utils/DBConnection.java` and update your MySQL credentials:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/quizapp";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password_here";
```

---

### 3 Compile the Project

```bash
javac --module-path ~/Downloads/javafx-sdk-21.0.7/lib \
--add-modules javafx.controls,javafx.fxml \
-cp "lib/mysql-connector-j-8.3.0.jar" -d out $(find src -name "*.java")
```

---

### 4 Run the App

```bash
java --module-path ~/Downloads/javafx-sdk-21.0.7/lib \
--add-modules javafx.controls,javafx.fxml \
-cp "out:lib/mysql-connector-j-8.3.0.jar" Main
```

---


##  Default Admin
To log in as an admin and manage quizzes/questions:
- **Username:** `admin`
- **Password:** `admin123`
If needed, insert an admin manually:
```sql
INSERT INTO users (username, email, password, role)
VALUES ('admin', 'admin@example.com', '<hashed_password>', 'ADMIN');

