-- Migration: V2_add_provider_id_column
-- Author: pepega
-- Description: Create provider_id column to store ids from providers;
-- Create an index for provider_id and auth_provider columns.

ALTER TABLE users ADD provider_id TEXT DEFAULT NULL;
CREATE INDEX idx_users_provider ON users(provider_id, auth_provider);