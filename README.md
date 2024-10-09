## Overview
This project is a User Management System built using Spring Boot and PostgreSQL. It provides a simple interface for creating, updating, and deleting user records, demonstrating the principles of RESTful architecture. The application follows the **Model-View-Controller (MVC)** design pattern to separate concerns and improve maintainability.

## Features
- **User Management**: Create, update, view, and delete users.
- **Data Persistence**: User data is stored in a PostgreSQL database.
- **RESTful API**: Supports standard HTTP methods for managing users.

## Technologies Used
- **Spring Boot**: Framework for building the application.
- **PostgreSQL**: Database for storing user data.
- **Thymeleaf**: Templating engine for rendering views.
- **Spring Web**: For handling web requests and responses.
- **Lombok**: To reduce boilerplate code in model classes.
- **Maven**: Dependency management and build tool.

## Screenshots

### User List Page
![img.png](img.png)

### Create User Page
![img_1.png](img_1.png)
- After creating a user, we can see a new row added.
![img_2.png](img_2.png)
- This indicates that a new entry has been added to our database.
![img_3.png](img_3.png)

### Update User Page
![img_4.png](img_4.png)
- We can change information about our users. For instance, we changed the age of Mihaela.
- After submitting the form, the information in our database is also updated.
![img_6.png](img_6.png)
- Changes are reflected on our website as well.
![img_7.png](img_7.png)

### UUID in Search Bar
- The UUID is displayed in the search bar after navigating to `/update/{uuid}`.
![img_8.png](img_8.png)
- Example UUID: `926c25c2-f1ba-4991-a5b7-356e9e248134`.

### Delete User Action
- The delete action works automatically without navigating to another page.
![img_9.png](img_9.png)
#### Our button of delete do it without asking
- The delete button operates seamlessly, and we can see the corresponding change in our database.
![img_10.png](img_10.png)





