delete from categoria where categoria_pai_id is not null;
delete from categoria;

alter table categoria auto_increment = 1;

insert into categoria (descricao) values ('Informática');
insert into categoria (descricao) values ('PLC');
insert into categoria (descricao) values ('Drives');

insert into categoria (descricao, categoria_pai_id) values ('Computadores', 1);
insert into categoria (descricao, categoria_pai_id) values ('Notebooks', 1);
insert into categoria (descricao, categoria_pai_id) values ('Tablets', 1);
insert into categoria (descricao, categoria_pai_id) values ('Monitores', 1);
insert into categoria (descricao, categoria_pai_id) values ('Impressoras', 1);
insert into categoria (descricao, categoria_pai_id) values ('Acessórios', 1);

insert into categoria (descricao, categoria_pai_id) values ('S7-200', 2);
insert into categoria (descricao, categoria_pai_id) values ('S7-400', 2);
insert into categoria (descricao, categoria_pai_id) values ('S7-1200', 2);
insert into categoria (descricao, categoria_pai_id) values ('S7-1500', 2);
insert into categoria (descricao, categoria_pai_id) values ('CompactLogix L36Rm', 2);

insert into categoria (descricao, categoria_pai_id) values ('G110', 3);
insert into categoria (descricao, categoria_pai_id) values ('G120', 3);
insert into categoria (descricao, categoria_pai_id) values ('G120c', 3);
insert into categoria (descricao, categoria_pai_id) values ('PowerFlex 40', 3);