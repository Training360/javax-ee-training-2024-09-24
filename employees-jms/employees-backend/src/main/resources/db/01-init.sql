create sequence employees_SEQ start with 1 increment by 50;
create sequence log_entries_SEQ start with 1 increment by 50;
create table employees (id bigint not null, emp_name varchar(255), primary key (id));
create table log_entries (id bigint not null, message varchar(255), primary key (id));