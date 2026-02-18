# E-Commerce Product API

A RESTful API for managing e-commerce product catalogs built with Spring Boot 3.2.2 and PostgreSQL.

## 📋 Project Overview

This is a web technology assignment that implements a complete CRUD (Create, Read, Update, Delete) API for managing products in an e-commerce system. The application demonstrates REST API best practices with proper HTTP status codes and error handling.

## ✨ Features

- ✅ **Add Products** - Create new products with detailed information
- ✅ **View All Products** - Retrieve complete product catalog
- ✅ **View Product Details** - Get specific product information by ID
- ✅ **Update Products** - Modify existing product details
- ✅ **Delete Products** - Remove products from inventory
- ✅ **PostgreSQL Database** - Persistent data storage
- ✅ **JSON Request/Response** - Standardized data format

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| **Framework** | Spring Boot 3.2.2 |
| **Language** | Java 21 |
| **Build Tool** | Maven |
| **Database** | PostgreSQL |
| **ORM** | Spring Data JPA with Hibernate |
| **API Style** | REST |

## 📦 Project Structure

```
question4_E-CommerceProductapi/
├── src/main/
│   ├── java/auca/ac/rw/restfullApiAssignment/
│   │   ├── RestfullApiAssignmentApplication.java  (Main entry point)
│   │   ├── controller/
│   │   │   └── ProductController.java            (REST endpoints)
│   │   ├── modal/
│   │   │   └── Product.java                      (Entity/Model)
│   │   ├── repository/
│   │   │   └── ProductRepository.java            (Data access)
│   │   └── service/
│   │       └── ProductService.java               (Business logic)
│   └── resources/
│       └── application.properties                (Configuration)
├── pom.xml                                       (Maven dependencies)
└── README.md                                     (This file)
```

## 🗄️ Database Schema

### Product Table

| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT | Primary Key - Unique product identifier |
| `name` | VARCHAR | Product name |
| `description` | VARCHAR | Detailed product description |
| `price` | DOUBLE | Product price |
| `category` | VARCHAR | Product category |
| `stock_quantity` | INT | Available stock count |

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:
- **Java 21** or higher
- **Maven 3.8+**
- **PostgreSQL 12+**
- **Git**

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/devderrickshema/RUTAGANIRA-SHEMA-Derrick_26506.git
   cd question4_E-CommerceProductapi
   ```

2. **Create PostgreSQL Database**
   ```sql
   CREATE DATABASE ecommerce_db;
   ```

3. **Configure Database Connection**
   
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD
   ```

4. **Build the Project**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   The API will be available at: `http://localhost:8080`

## 📚 API Endpoints

### Base URL
```
http://localhost:8080/api/products
```

### 1. Add Product
**Endpoint:** `POST /api/products/addProduct`

**Request Body:**
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "category": "Electronics",
  "stockQuantity": 50
}
```

**Response:** 
```json
"Product saved successfully."
```
**Status Codes:** `201 Created` | `409 Conflict` (if product already exists)

---

### 2. Get All Products
**Endpoint:** `GET /api/products`

**Response:**
```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 50
  },
  {
    "id": 2,
    "name": "Mouse",
    "description": "Wireless mouse",
    "price": 29.99,
    "category": "Accessories",
    "stockQuantity": 200
  }
]
```
**Status Code:** `200 OK`

---

### 3. Get Product by ID
**Endpoint:** `GET /api/products/{id}`

**Example:** `GET /api/products/1`

**Response:**
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "category": "Electronics",
  "stockQuantity": 50
}
```
**Status Codes:** `200 OK` | `404 Not Found`

---

### 4. Update Product
**Endpoint:** `PUT /api/products/{id}`

**Example:** `PUT /api/products/1`

**Request Body:**
```json
{
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop",
  "price": 1299.99,
  "category": "Electronics",
  "stockQuantity": 45
}
```

**Response:**
```json
"Product updated successfully."
```
**Status Codes:** `200 OK` | `404 Not Found`

---

### 5. Delete Product
**Endpoint:** `DELETE /api/products/{id}`

**Example:** `DELETE /api/products/1`

**Response:**
```json
"Product deleted successfully."
```
**Status Codes:** `200 OK` | `404 Not Found`

## 🧪 Testing the API

### Using cURL

```bash
# Add a product
curl -X POST http://localhost:8080/api/products/addProduct \
  -H "Content-Type: application/json" \
  -d '{"id":1,"name":"Laptop","description":"Gaming laptop","price":1299.99,"category":"Electronics","stockQuantity":50}'

# Get all products
curl http://localhost:8080/api/products

# Get product by ID
curl http://localhost:8080/api/products/1

# Update product
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Laptop","price":999.99,"category":"Electronics","stockQuantity":45}'

# Delete product
curl -X DELETE http://localhost:8080/api/products/1
```

### Using Postman

1. Import the endpoints into Postman
2. Set the base URL to `http://localhost:8080/api/products`
3. Create requests for each endpoint
4. Test with various product data

## 🔧 Configuration

Key application properties in `application.properties`:

| Property | Default | Description |
|----------|---------|-------------|
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/ecommerce_db` | Database connection URL |
| `spring.datasource.username` | `postgres` | Database username |
| `spring.datasource.password` | `P@ssw0rd2025` | Database password |
| `spring.jpa.hibernate.ddl-auto` | `update` | Auto-update database schema |
| `spring.jpa.show-sql` | `true` | Log SQL queries |

## 📝 Course Information

- **Course:** Web Technology (Semester VII)
- **Assignment:** Question 4 - E-Commerce Product API
- **Group:** Group E

## 👨‍💻 Authors

- RUTAGANIRA Derrick (SHEMA)
- ID: 26506

## 📄 License

This project is part of an academic assignment at AUCA (Africa University of Central Africa).
