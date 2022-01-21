INSERT INTO usr (username, password, active)
VALUES ('Admin', '$2a$08$YnFtFbTD.PtlZ7eWvjKOFuhXEJXqMRlRFe9JmIVCMwRzTBQb3vZ/S', true);

INSERT INTO user_role (user_id, roles)
VALUES (1, 'ADMIN'),
       (1, 'USER');