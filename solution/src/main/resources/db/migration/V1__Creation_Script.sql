CREATE TABLE expense (
  id     SERIAL PRIMARY KEY,
  amount DECIMAL(10,2),
  vat    DECIMAL(10,2),
  reason VARCHAR(255),
  date   DATE
);