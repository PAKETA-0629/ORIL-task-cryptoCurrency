CREATE TABLE pairs_prices (

    id SERIAL PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    symbol1 VARCHAR(15) NOT NULL,
    symbol2 VARCHAR(15) NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL
);