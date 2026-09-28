-- Migration: V3__delete_token_column
-- Author: pepega
-- Description: Delete the token column from the users table

ALTER TABLE users DROP COLUMN token