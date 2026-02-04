# SearchServlet - RUTAGANIRA SHEMA Derrick (26506)

A Java servlet application that handles search queries and redirects to Google search.

## Developer
**RUTAGANIRA SHEMA Derrick** - devderrickshema  
**Student ID**: 26506

## Features
- Accepts search queries via POST request
- URL encodes search terms for safe transmission
- Redirects to Google search with the query
- Handles empty queries gracefully

## Quick Start
```bash
mvn clean package
mvn tomcat7:run
```

Access the application at: http://localhost:8081/SearchServlet/

## Project Structure
- Maven-based Java web application
- Standard servlet implementation
- Web.xml configuration for servlet mapping
- Clean separation of concerns