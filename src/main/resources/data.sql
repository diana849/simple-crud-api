INSERT INTO project(title, description, version)
VALUES ('title', 'description', 0);

INSERT INTO project(title, description, version)
VALUES ('title2', 'description2', 0);

INSERT INTO user_table(first_name, last_name, email, zip_code, version)
VALUES ('Jan', 'Kowalski', 'jan.kowalski@gmail.com', '01-105', 0);

INSERT INTO task(name, description, status, project_id, user_id, version)
VALUES ('task', 'description', 'status', 1, 1, 0);