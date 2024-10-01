/*
Let's say you're administering a SQL database system for a school. The
existing database is already in place when you arrive, and has a large
amount of existing data. The schema is provided below.

    Design a query that, for every student, tells us their ID, name, and their
    primary teacher's name. You should include all students in the result, even
    if they do not have a primary teacher.
*/

drop table if exists Grades;
drop table if exists Assignments;
drop table if exists Students;
drop table if exists Teachers;

/*
Begin schema
*/

create table Teachers (
id int primary key,
name varchar(16) not null,
classroom int not null
);

create table Students (
id int primary key,
name varchar(16),
primary_teacher_id int,
FOREIGN KEY (primary_teacher_id) REFERENCES Teachers(id)
);

create table Assignments (
id int primary key,
teacher_id int not null,
FOREIGN KEY (teacher_id) REFERENCES Teachers(id)
);


create table Grades (
student_id int,
assignment_id int,
grade decimal (4,1),
PRIMARY KEY (student_id, assignment_id),
FOREIGN KEY (student_id) REFERENCES Students(id),
FOREIGN KEY (assignment_id) REFERENCES Assignments(id)
);

/* End schema */

/* Begin test data */

insert into Teachers values
(4, 'Mr. Feeny', 301), (8, 'Mr. Cooper', 260), (30, 'Ms. Finster', 301);

insert into Students values
(1, 'Bobby', 4), (2, 'Susie', 8), (3, 'Deborah', 8),
(4, 'Anand', 4), (5, 'Robert', 4), (6, 'Claire', 4),
(8, 'Petra', 4), (9, 'Bruce', null), (10, 'Andrew', 4),
(11, 'Kim', 4);

insert into Assignments values(1, 4), (2, 8);

insert into Grades values
(1, 1, 100), (1, 2, 50),
(2, 1, 100), (2, 2, 100),
(3, 1, 40), (3, 2, 8),
(4, 1, 80), (4, 2, 81),
(5, 1, 30), (5, 2, 60),
(6, 2, 90),
(9, 1, 65), (9, 2, 65),
(10, 1, 85);

/* End test data */

/* Add your own statements below
select s.id, s.name, t.name from Students s
left outer join Teachers t on s.primary_teacher_id = t.id;
*/

/*
We want to identify the school's top-performing students. Design a query, prepared statement, or stored procedure that will select only those students whose average score (across all assignments) are in the top half of all students, and rank them by their score. Exclude those students who have never completed an assignment.

Expected output:

+------------+-----------+
| student_id | avggrade  |
+------------+-----------+
|          2 | 100.00000 |
|          6 |  90.00000 |
|         10 |  85.00000 |
|          4 |  80.50000 |
+------------+-----------+
*/

Task 1: Query for student ID, name, and their primary teacher's name (including all students even if they don't have a primary teacher):

SELECT s.id AS student_id,
s.name AS student_name,
t.name AS primary_teacher_name
FROM Students s
LEFT OUTER JOIN Teachers t
ON s.primary_teacher_id = t.id;
This query uses a LEFT OUTER JOIN to include all students from the Students table, even if they do not have a matching primary_teacher_id in the Teachers table (i.e., if a student does not have a primary teacher).
The result will show the student's ID, name, and the name of their primary teacher, if available.
Task 2: Query to find top-performing students whose average score is in the top half:

The approach here involves:

Calculating the average grade for each student.
Ranking students by their average grade.
Selecting students whose average grade is in the top half, excluding students who haven't completed any assignments.

WITH StudentGrades AS (
SELECT g.student_id,
AVG(g.grade) AS avggrade
FROM Grades g
GROUP BY g.student_id
),
GradeRanking AS (
SELECT student_id,
avggrade,
NTILE(2) OVER (ORDER BY avggrade DESC) AS rank_group
FROM StudentGrades
)
SELECT student_id, avggrade
FROM GradeRanking
WHERE rank_group = 1
ORDER BY avggrade DESC;