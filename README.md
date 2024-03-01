# Student management system
#### Student management system using Spring, with H2 and Thymeleaf
This project features a custom login page that verifies the completeness and accuracy of user input. Upon successful authentication, users are directed to the Students page, displaying a comprehensive list of students retrieved from the database. Users with the "USER" role are restricted from accessing the "Add student" functionality, which is exclusively available to administrators. Authentication and authorization are implemented using @PreAuthorize and @EnableMethodSecurity annotations.

Furthermore, users are empowered to edit and delete student records directly from the database. Validation checks are enforced on the edit page to ensure data integrity.

I used Spring Boot 3, Spring MVC, Spring Data JPA (Hibernate), H2 and Thymeleaf.

![2024-02-26_09h28_09](https://github.com/Romanhan/StudentManagementSystem/assets/65030995/8570e76a-439f-4488-814b-6d2c015db13d)
