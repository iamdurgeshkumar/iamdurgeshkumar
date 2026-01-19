    CREATE TABLE restaurant(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    is_Deleted boolean DEFAULT FALSE
    );