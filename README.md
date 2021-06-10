# Transaction-Statistics
API is to calculate realtime statistic from the last 60 seconds

POST /transactions

Every Time a new transaction happened, this endpoint will be called. Body:

{
  "amount": 12.3,
  "timestamp": 1478192204000
}

Where:

amount - transaction amount
timestamp - transaction time in epoch in millis in UTC time zone (this is not current timestamp)

-----------------------------------------------------------------------------------------------------

GET /statistics

This is the main endpoint of this task, this endpoint have to execute in constant time and memory (O(1)). It returns the statistic based on the transactions which happened in the last 60 seconds

Returns:

{
  "sum": 1000,
  "avg": 100,
  "max": 200,
  "min": 50,
  "count": 10
}
---------------------------------------------------------------------------------------------------------
The API needs the following endpoints: 
● POST /transactions​ – called every time a transaction is made. It is also the sole input of this rest API. 
● GET /statistics​ – returns the statistic based of the transactions of the last 60 seconds. 
● DELETE /transactions​ – deletes all transactions. 
