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


**Visual documentation** 

[openapi.json](https://github.com/user-attachments/files/16904627/openapi.json)

![CF77A3A5-CB7F-4E71-9FB7-E3A13D75594C](https://github.com/user-attachments/assets/8a502af0-b578-4a23-91ef-227e1d48bc1f)

![80936BEC-BD75-41F7-BFA3-0A2712CD8869](https://github.com/user-attachments/assets/5d79912b-6f06-4f6d-9617-d9e7216c32cf)

**Tests**

![image001](https://github.com/user-attachments/assets/31014d42-e830-4980-a1e8-09b6343725b4)

**Entity Relationship Diagram**

![image](https://github.com/user-attachments/assets/3ea3bd86-e965-47ed-977f-1707dd4c9c12)


