ALTER TABLE usuario ADD COLUMN reset_password_token VARCHAR(255);
ALTER TABLE usuario ADD COLUMN reset_password_token_expiry_date TIMESTAMP;
