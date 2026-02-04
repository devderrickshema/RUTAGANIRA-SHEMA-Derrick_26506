# Login Servlet & Search Servlet  **RUTAGANIRA SHEMA Derrick (26506) Group E** 



A Java Servlet–based web application implementing **two independent servlet tasks**:
- Login Servlet
- Search Servlet

Both servlets are managed in the same repository but clearly differentiated to match separate assignment questions.

---

## 👨‍💻 Developer
**Name:** RUTAGANIRA SHEMA Derrick  
**Student ID:** 26506  
**GitHub:** devderrickshema  

---

## 📌 Features

### 🔐 Login Servlet
- Accepts user login credentials
- Processes form submission using servlets
- Demonstrates request handling and response flow
- Servlet mapping via `web.xml`

### 🔎 Search Servlet
- Accepts search queries via POST request
- Handles empty input gracefully
- URL-encodes search queries safely
- Redirects users to Google search results

---

## 🚀 Quick Start

### Prerequisites
- Java JDK 8+
- Maven
- Apache Tomcat (via Maven plugin)

### Run the Project
```bash
mvn clean package
mvn tomcat7:run
Access URLs

Login Servlet:
http://localhost:8081/LoginServlet/

Search Servlet:
http://localhost:8081/SearchServlet/


