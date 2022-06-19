create table account
(
	id      bigserial primary key,
	account char(58) not null constraint idx_account_account unique,
	secret  varchar(256) not null
);
