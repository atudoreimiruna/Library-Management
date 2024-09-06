# Library-Management

# Business Requirements

**1. User Management** The system must allow administrators to add, update, retrieve, and delete users via API endpoints. This includes functionalities like adding multiple users at once and updating user information such as names and email addresses.

**2. Book Management** The application must provide endpoints for adding, updating, retrieving, and deleting book records. Each book should have details like title, ISBN, quantity, status (available, reserved, borrowed, or lost), and associated authors.

**3. Borrowing Functionality** The system must allow users to borrow books through API calls. Borrowing records should capture the borrow date, return date, and any associated fines. It must be possible to add multiple borrowing records at once.

**4. Category Management** The application must support the creation, updating, and deletion of book categories. Each category should include a name and description, with the ability to link it to multiple books.

**5. Author Management** The system must enable administrators to manage author records. This includes adding new authors, updating author details, and deleting authors from the system.

**6. Availability Tracking** The system must provide an endpoint to check the availability of books based on their status (e.g., available, reserved, borrowed, or lost), allowing for real-time inventory tracking.

**7. User Borrowing History** The system must allow the retrieval of a user's borrowing history through an API, showing detailed information about all borrowed books, including borrow and return dates, and any applicable fines.

**8. Validation and Error Handling** The application must validate all incoming data for the API calls, ensuring that required fields are populated and data types are correct. Proper error messages should be returned for invalid requests (e.g., bad requests or missing data).

**9. Fine Management** The system should include functionality to track fines associated with late returns of borrowed books. Fines must be recorded against each borrowing record and visible through the user's borrowing history.

**10. Batch Operations** The application must allow batch operations for adding multiple records at once (e.g., adding multiple books, users, or borrowings), ensuring efficient bulk management for large datasets.

![CF77A3A5-CB7F-4E71-9FB7-E3A13D75594C](https://github.com/user-attachments/assets/8a502af0-b578-4a23-91ef-227e1d48bc1f)

![80936BEC-BD75-41F7-BFA3-0A2712CD8869](https://github.com/user-attachments/assets/5d79912b-6f06-4f6d-9617-d9e7216c32cf)

**Visual documentation** 
[openapi.json](https://github.com/user-attachments/files/16904627/openapi.json){
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/api/user/updateUser": {
      "put": {
        "tags": [
          "UserController"
        ],
        "summary": "update user",
        "description": "PUT method for updating the informations of an user",
        "operationId": "updateUser",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UserPutDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/book/updateBook": {
      "put": {
        "tags": [
          "BookController"
        ],
        "summary": "update book",
        "description": "POST method for updating a book",
        "operationId": "updateBook",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/BookPutDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/user/addUsers": {
      "post": {
        "tags": [
          "UserController"
        ],
        "summary": "add users",
        "description": "POST method for adding a list of new users",
        "operationId": "addUsers",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "array",
                "items": {
                  "$ref": "#/components/schemas/User"
                }
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/category/addCategory": {
      "post": {
        "tags": [
          "CategoryController"
        ],
        "summary": "add category",
        "description": "POST method for adding a new category of books",
        "operationId": "addCategory",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/CategoryDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "integer",
                  "format": "int64"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/borrowing/addBorrowings/{userId}": {
      "post": {
        "tags": [
          "BorrowingController"
        ],
        "summary": "add borrowings",
        "description": "POST method for adding a list of borrowings for an user",
        "operationId": "addBorrowings",
        "parameters": [
          {
            "name": "userId",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "array",
                "items": {
                  "$ref": "#/components/schemas/BorrowingInfoDto"
                }
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/book/addBooks": {
      "post": {
        "tags": [
          "BookController"
        ],
        "summary": "add books",
        "description": "POST method for adding a list of books",
        "operationId": "addBooks",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "array",
                "items": {
                  "$ref": "#/components/schemas/BookDto"
                }
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/book/addBook": {
      "post": {
        "tags": [
          "BookController"
        ],
        "summary": "add book",
        "description": "POST method for adding a book",
        "operationId": "addBook",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/BookDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "integer",
                  "format": "int64"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/author/addAuthor": {
      "post": {
        "tags": [
          "AuthorController"
        ],
        "summary": "add author",
        "description": "POST method for adding an author",
        "operationId": "addAuthor",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AuthorDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "integer",
                  "format": "int64"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/user/getUser/{id}": {
      "get": {
        "tags": [
          "UserController"
        ],
        "summary": "get user",
        "description": "GET method for receiving an user by id",
        "operationId": "getUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UserBorrowingDto"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/book/available": {
      "get": {
        "tags": [
          "BookController"
        ],
        "summary": "get available books",
        "description": "GET method for receiving a list of available books",
        "operationId": "getAvailableBooks",
        "parameters": [
          {
            "name": "status",
            "in": "query",
            "required": false,
            "schema": {
              "type": "string",
              "enum": [
                "AVAILABLE",
                "RESERVED",
                "BORROWED",
                "LOST"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/AvailableBookDto"
                  }
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/user/deleteUser/{id}": {
      "delete": {
        "tags": [
          "UserController"
        ],
        "summary": "delete user",
        "description": "DELETE method for removing an user by id",
        "operationId": "delete",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/category/deleteCategory/{id}": {
      "delete": {
        "tags": [
          "CategoryController"
        ],
        "summary": "delete category",
        "description": "DELETE method for removing a category of books",
        "operationId": "delete_1",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/book/deleteBook/{id}": {
      "delete": {
        "tags": [
          "BookController"
        ],
        "summary": "delete book",
        "description": "DELETE method for removing a book by id",
        "operationId": "delete_2",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/author/deleteAuthor/{id}": {
      "delete": {
        "tags": [
          "AuthorController"
        ],
        "summary": "delete author",
        "description": "DELETE method for removing an author by id",
        "operationId": "delete_3",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "object"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "UserPutDto": {
        "required": [
          "id"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "email": {
            "type": "string"
          }
        }
      },
      "BookPutDto": {
        "required": [
          "id"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "title": {
            "type": "string"
          },
          "isbn": {
            "type": "string"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          }
        }
      },
      "Author": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "bookAuthor": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/BookAuthor"
            }
          }
        }
      },
      "Book": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "title": {
            "type": "string"
          },
          "isbn": {
            "type": "string"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "status": {
            "type": "string",
            "enum": [
              "AVAILABLE",
              "RESERVED",
              "BORROWED",
              "LOST"
            ]
          },
          "borrowing": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Borrowing"
            }
          },
          "bookAuthor": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/BookAuthor"
            }
          },
          "category": {
            "$ref": "#/components/schemas/Category"
          }
        }
      },
      "BookAuthor": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "dateOfWriting": {
            "type": "string",
            "format": "date"
          },
          "book": {
            "$ref": "#/components/schemas/Book"
          },
          "author": {
            "$ref": "#/components/schemas/Author"
          }
        }
      },
      "Borrowing": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "borrowDate": {
            "type": "string",
            "format": "date"
          },
          "returnDate": {
            "type": "string",
            "format": "date"
          },
          "fineAmount": {
            "minimum": 0,
            "type": "number"
          },
          "book": {
            "$ref": "#/components/schemas/Book"
          },
          "user": {
            "$ref": "#/components/schemas/User"
          }
        }
      },
      "Category": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "books": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Book"
            }
          }
        }
      },
      "User": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "email": {
            "type": "string"
          },
          "borrowing": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Borrowing"
            }
          }
        }
      },
      "CategoryDto": {
        "type": "object",
        "properties": {
          "name": {
            "type": "string"
          },
          "description": {
            "type": "string"
          }
        }
      },
      "BorrowingInfoDto": {
        "type": "object",
        "properties": {
          "bookId": {
            "type": "integer",
            "format": "int64"
          },
          "borrowDate": {
            "type": "string",
            "format": "date"
          },
          "returnDate": {
            "type": "string",
            "format": "date"
          },
          "fineAmount": {
            "minimum": 0,
            "type": "number"
          }
        }
      },
      "BookDto": {
        "required": [
          "authorName",
          "isbn",
          "status",
          "title"
        ],
        "type": "object",
        "properties": {
          "title": {
            "type": "string"
          },
          "isbn": {
            "type": "string"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "authorName": {
            "type": "string"
          },
          "dateOfWriting": {
            "type": "string",
            "format": "date"
          },
          "status": {
            "type": "string",
            "enum": [
              "AVAILABLE",
              "RESERVED",
              "BORROWED",
              "LOST"
            ]
          }
        }
      },
      "AuthorDto": {
        "type": "object",
        "properties": {
          "name": {
            "type": "string"
          }
        }
      },
      "BookViewDto": {
        "required": [
          "status"
        ],
        "type": "object",
        "properties": {
          "bookId": {
            "type": "integer",
            "format": "int64"
          },
          "title": {
            "type": "string"
          },
          "isbn": {
            "type": "string"
          },
          "status": {
            "type": "string",
            "enum": [
              "AVAILABLE",
              "RESERVED",
              "BORROWED",
              "LOST"
            ]
          }
        }
      },
      "BorrowingDto": {
        "required": [
          "borrowingId"
        ],
        "type": "object",
        "properties": {
          "borrowingId": {
            "type": "integer",
            "format": "int64"
          },
          "borrowDate": {
            "type": "string",
            "format": "date"
          },
          "returnDate": {
            "type": "string",
            "format": "date"
          },
          "fineAmount": {
            "minimum": 0,
            "type": "number"
          },
          "book": {
            "$ref": "#/components/schemas/BookViewDto"
          }
        }
      },
      "UserBorrowingDto": {
        "type": "object",
        "properties": {
          "userId": {
            "type": "integer",
            "format": "int64"
          },
          "firstName": {
            "type": "string"
          },
          "lastName": {
            "type": "string"
          },
          "email": {
            "type": "string"
          },
          "borrowings": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/BorrowingDto"
            }
          }
        }
      },
      "AvailableBookDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "title": {
            "type": "string"
          },
          "isbn": {
            "type": "string"
          },
          "quantity": {
            "type": "integer",
            "format": "int32"
          },
          "authors": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      }
    }
  }
}

