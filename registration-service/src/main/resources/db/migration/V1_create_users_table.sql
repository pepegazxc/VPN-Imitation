-- Migration: V1_create_user_table
-- Author: pepega
-- Description: Create users table to store the required user information;
-- Create a function to verify the contact info the user used to register;
-- Create an unique index for user username.

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    cipher_phone_number VARCHAR(250) UNIQUE,
    cipher_email VARCHAR(250) UNIQUE,
    hash_password TEXT NOT NULL,

    token VARCHAR(250) UNIQUE NOT NULL,
    created_at timestamptz DEFAULT now(),

    auth_provider VARCHAR(20) DEFAULT 'LOCAL' NOT NULL,

    CONSTRAINT check_contact_info CHECK (
        (cipher_email  IS NOT NULL AND cipher_phone_number IS NULL)
        OR
        (cipher_email IS NULL AND cipher_phone_number IS  NOT NULL)
        or
        (cipher_email IS NOT NULL AND cipher_phone_number IS NOT NULL)
        )


);


