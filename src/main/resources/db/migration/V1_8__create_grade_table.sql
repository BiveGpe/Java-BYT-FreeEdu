CREATE TABLE grade (
    id int PRIMARY KEY AUTO_INCREMENT,
    value DECIMAL(3, 2) NOT NULL CHECK (value >= 0 AND value <= 6),
    student_id int NOT NULL REFERENCES student(id) ON DELETE CASCADE,
    teacher_id int NOT NULL REFERENCES teacher(id) ON DELETE CASCADE,
    subject ENUM('POLISH', 'MATH', 'GEOGRAPHY', 'SCIENCE', 'ART', 'HISTORY', 'SPORTS') NOT NULL,
    add_date DATE NOT NULL
);
