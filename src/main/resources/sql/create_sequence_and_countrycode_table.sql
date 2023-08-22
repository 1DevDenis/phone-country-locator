-- Create a sequence
CREATE SEQUENCE country_calling_codes_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create a table
CREATE TABLE country_calling_codes (
    id INT PRIMARY KEY DEFAULT nextval('country_calling_codes_sequence'),
    country VARCHAR(20),
    callingCode VARCHAR(10)
);