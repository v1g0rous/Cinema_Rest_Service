# Table of contents
<!-- TOC -->
* [Table of contents](#table-of-contents)
* [Cinema_Rest_Service](#cinema_rest_service)
    * [Main Business Features](#main-business-features)
    * [Installing](#installing)
    * [Run with IDE (__Intellij IDEA__)](#run-with-ide--__intellij-idea__-)
  * [Specification](#specification)
    * [Get available seats](#get-available-seats)
      * [URL](#url)
      * [Description](#description)
      * [Response](#response)
      * [Result codes](#result-codes)
      * [Response example](#response-example)
    * [Get stats](#get-stats)
      * [URL](#url)
      * [Description](#description)
      * [Request](#request)
      * [Request example](#request-example)
      * [Response](#response)
      * [Result codes](#result-codes)
      * [Response example](#response-example)
    * [Purchase ticket](#purchase-ticket)
      * [URL](#url)
      * [Description](#description)
      * [Request](#request)
      * [Request example](#request-example)
      * [Response](#response)
      * [Result codes](#result-codes)
      * [Response example](#response-example)
    * [Return ticket](#return-ticket)
      * [URL](#url)
      * [Description](#description)
      * [Request](#request)
      * [Request example](#request-example)
      * [Response](#response)
      * [Result codes](#result-codes)
      * [Response example](#response-example)
<!-- TOC -->

# Cinema_Rest_Service
Application represents cinema theatre service
That is REST service, having no UI.
Each HTTP command returns response in JSON format.

### Main Business Features
1. Get available seats
2. Purchase tickets
3. Return ticket
4. Cinema theatre statistics

### Installing
To install app you need:
- Install jdk 17


### Run with IDE (__Intellij IDEA__)
1. Open the project in IDE
2. Go to Main class
3. IDE Menu -> Run
4. Embedded Tomcat should start
5. Send HTTP requests using way you like (IDE, postman, cURL)
6. Get response

## Specification
Note: for local usage endpoint should start with localhost:28852
### Get available seats
#### URL
GET /seats
#### Description
This method gets information about seats that are available for booking
#### Response
| Field name      | Required | Type                  | Description                                                          |
|-----------------|----------|-----------------------|----------------------------------------------------------------------|
| total_rows      | yes      | Integer               | Total cinema theater rows (including both purchased and available    |
| total_columns   | yes      | Integer               | Total cinema theater columns (including both purchased and available |
| available_seats | yes      | Array of Seat objects | List of seats with info about each seat                              |
| row             | yes      | Integer               | Single seat row number                                               |
| column          | yes      | Integer               | Single seat column number                                            |
| price           | yes      | Integer               | Single seat price                                                    |

#### Result codes
| resultCode | Description                     |
|------------|---------------------------------|
| OK         | Seats data successfully returned |


#### Response example
```json
{
  "total_rows": 2,
  "total_columns": 2,
  "available_seats": [
    {
      "row": 1,
      "column": 1,
      "price": 10
    },
    {
      "row": 1,
      "column": 2,
      "price": 10
    },
    {
      "row": 2,
      "column": 1,
      "price": 10
    },
    {
      "row": 2,
      "column": 2,
      "price": 10
    }
  ]
}
```


### Get stats
#### URL
GET /stats
#### Description
This method returns Cinema Theatre statistics.
#### Request
| Field name | Required | Type   | Description                                                 |
|------------|----------|--------|-------------------------------------------------------------|
| password   | yes      | String | **Query parameter**: total amount for all purchased tickets |

#### Request example
```html
http://localhost:28852/stats?password=super_secret123124
```

#### Response
| Field name                  | Required | Type    | Description                            |
|-----------------------------|----------|---------|----------------------------------------|
| current_income              | yes      | Integer | Total amount for all purchased tickets |
| number_of_available_seats   | yes      | Integer | Total number of available seats        |
| number_of_purchased_tickets | yes      | Integer | Total number of purchased tickets      |

#### Result codes
| resultCode   | Description                   |
|--------------|-------------------------------|
| OK           | Stats successfully returned   |
| Unauthorized | Password is null or incorrect |


#### Response example
```json
{
  "current_income": 20,
  "number_of_available_seats": 79,
  "number_of_purchased_tickets": 2
}
```

### Purchase ticket
#### URL
POST /purchase
#### Description
This method books a seat in Cinema Theatre and provides unique booking token.
#### Request
| Field name | Required | Type    | Description |
|------------|----------|---------|-------------|
| row        | yes      | Integer | Seat row    |
| column     | yes      | Integer | Seat column |

#### Request example
```json
{
  "row": 1,
  "column": 2
}
```
#### Response
| Field name | Required | Type    | Description               |
|------------|----------|---------|---------------------------|
| token      | yes      | String  | Unique seat booking token |
| row        | yes      | Integer | Seat row                  |
| column     | yes      | Integer | Seat column               |
| price      | yes      | Integer | Seat price                |

#### Result codes
| resultCode   | Description                                  |
|--------------|----------------------------------------------|
| OK           | Ticket successfully booked                   |
| BAD_REQUEST  | Seat has already been taken or doesn't exist |


#### Response example
```json
{
  "token": "34b3c908-b672-4469-9ded-1cc45eb281d5",
  "ticket": {
    "row": 1,
    "column": 2,
    "price": 10
  }
}
```

### Return ticket
#### URL
POST /return
#### Description
This method unbooks a seat in Cinema Theatre if client doesn't need it anymore.
**Required body parameter**: seat row and seat column
#### Request
| Field name | Required | Type   | Description               |
|------------|----------|--------|---------------------------|
| token      | yes      | String | Unique seat booking token |

#### Request example
```json
{
  "token": "d92917cd-b60f-4f60-a063-7acac0f8908d"
}
```
#### Response
| Field name | Required | Type    | Description               |
|------------|----------|---------|---------------------------|
| row        | yes      | Integer | Seat row                  |
| column     | yes      | Integer | Seat column               |
| price      | yes      | Integer | Seat price                |

#### Result codes
| resultCode   | Description                  |
|--------------|------------------------------|
| OK           | Ticket successfully returned |
| BAD_REQUEST  | Token is null or incorrect   |


#### Response example
```json
{
  "returned_ticket": {
    "row": 1,
    "column": 2,
    "price": 10
  }
}
```