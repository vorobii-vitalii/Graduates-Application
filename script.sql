create database if not exists graduates;

use graduates;

create table degree (
  degree_id serial primary key,
  name text unique
);

create table student (
  student_id serial primary key,
  first_name text not null,
  last_name text not null,
  current_position text
);

create table publications (
	publication_id serial primary key,
  	title text not null,
  	url text
);

create table student_degree (
  	student_degree_id serial primary key,
  	student_id integer references student(student_id),
  	degree_id integer references degree(degree_id),
  	publication_id integer references publications(publication_id) unique,
  	admission_year integer not null,
  	graduation_year integer not null,
  	with_honour boolean
);