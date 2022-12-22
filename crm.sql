create database crm_app;

USE crm_app;

drop database crm_app;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    phone_number VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_detail (
	user_id INT NOT NULL,
    age int,
    cmnd varchar(12),
    
    PRIMARY KEY (user_id),
    foreign key(user_id) references users(id)
);


insert into users_detail(user_id, age, cmnd) values(1,18,"283746284739");
insert into users_detail(user_id, age, cmnd) values(2,21,"1234543212");

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);


ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản lý trang");
INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");
INSERT INTO roles( name, description ) VALUES ("test", "Nhân");

INSERT INTO status( name ) VALUES ("Chưa thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");
INSERT INTO status( name ) VALUES ("Đã hoàn thành");

INSERT INTO users(email, password, fullname, avatar, role_id) VALUES ("nguyenvana@gmail.com", "123456", "Nguyễn Văn A", "", 1);
INSERT INTO users(email, password, fullname, avatar, role_id) VALUES ("nguyenvanb@gmail.com", "123456", "Nguyễn Văn B", "", 2);
INSERT INTO users(email, password, fullname, avatar, role_id) VALUES ("nguyenvanc@gmail.com", "123456", "Nguyễn Văn C", "", 3);
INSERT INTO users(email, password, fullname, role_id) VALUES ("nguyenvanc@gmail.com", "123456", "Nguyễn Văn D", 3);

INSERT INTO roles(id, name, description ) VALUES (1, "ROLE_ADMIN", "Quản lý trang");
INSERT INTO roles(id, name, description ) VALUES (2, "ROLE_MANAGER", "Quản lý");
INSERT INTO roles(id, name, description ) VALUES (3, "ROLE_USER", "Nhân viên");

INSERT INTO jobs(name, start_date, end_date) VALUES ("JOB 1", '2022-11-28' , '2022-11-29')
delete from jobs where id = 1
update jobs set name = "JOB 1", start_date = '2022-11-28', end_date = '2022-11-30' where id = ?

insert into tasks(name, start_date, end_date, job_id, user_id, status_id) values("task 1", '2022-11-28' , '2022-11-29', 1, 1, 1)
insert into tasks(name, start_date, end_date, job_id, user_id, status_id) values("task 1", '2022-11-28' , '2022-11-29', 1, 1, 2)
insert into tasks(name, start_date, end_date, job_id, user_id, status_id) values("task 1", '2022-11-28' , '2022-11-29', 1, 1, 3)
update tasks set name = ?, start_date = ?, end_date = ?, job_id = ?, user_id = ?, status_id = ? where id = ?;

select * from users u where u.email = "nguyenvana@gmail.com" and u.password = "123456";
select * from roles;
select u.id, u.email, u.fullname, r.name as role from users u join roles r on u.role_id = r.id;
select * from users
select * from jobs
select * from status
select * from tasks

select * from jobs where id = 1


select t.id, t.name as taskName, j.name as jobName, u.fullname as userName, t.start_date, t.end_date, s.name as status from tasks t join jobs j on t.job_id = j.id join users u on t.user_id = u.id join status s on t.status_id = s.id;

select t.id, t.name as taskName, j.name as jobName, t.start_date, t.end_date, s.name as status from tasks t join jobs j on t.job_id = j.id join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ?;

select u.id, u.fullname, u.email, count( distinct if(s.id = 1, t.id, null)) as yetStartTask, count( distinct if(s.id = 2, t.id, null)) as doingTask, count( distinct if(s.id = 3, t.id, null)) as completedTask, count(t.id) as totalTask
from tasks t 
right join users u on t.user_id = u.id
left join status s on t.status_id = s.id
where u.id = 1
group by u.id

select u.id, u.fullname
from tasks t 
join users u on t.user_id = u.id
where t.job_id = 1
group by u.id

select t.name, t.start_date, t.end_date, s.id
from tasks t
join status s on t.status_id = s.id
where t.user_id = 1 and t.job_id = 1


select j.id, count( distinct if(s.id = 1, t.id, null)) as yetStartTask, count( distinct if(s.id = 2, t.id, null)) as doingTask, count( distinct if(s.id = 3, t.id, null)) as completedTask, count(t.id) as totalTask
from tasks t 
right join jobs j on t.job_id = j.id
left join status s on t.status_id = s.id
where j.id = 1
group by j.id


select t.name as taskName, j.name as jobName, t.start_date, t.end_date, s.name as statusName
from tasks t
join jobs j on t.job_id = j.id
join status s on t.status_id = s.id
where t.id = 3 


select u.id, u.fullname , u.email, u.password, u.phone_number, r.id as roleId, r.name as roleName from users u join roles r on r.id = u.role_id where u.id = 1


update roles set name = "Vinh", description = "vinh" where id = 13
-- master data => đi hỏi BA , 