MVP HOTEL BOOKING APIs

1. Search hotel by location
   1. Overall

      |URL|Method|
      | :- | :- |
      |Local: http://localhost:8080/api/hotel?location={location}&start={start}&pageSize=1|GET|

   1. Query param

      |Param|Type|Required/ Optional|Default value|Description|
      | :- | :- | :- | :- | :- |
      |location|string|o|Emty string|Location wants to search|
      |start|integer|o|0|Starting index of search result (for pagination)|
      |pageSize|integer|o|20|Max size of result list (for pagination)|

   1. HTTP code

        |Code|Description|
        | :- | :- |
        |200|Success|
        |500|Server error|

   1. Response body

       |Field name|Type|Description|
       | :- | :- | :- |
       |total|integer|Total record of search result|
       |pageSize|integer|pageSize of search result|
       |rows|Array of json|Array of hotel details|

      1. Hotel details

          |Field name|Type|Description|
          | :- | :- | :- |
          |id|integer|Hotel id|
          |name|string|Hotel name|
          |address|string|Hotel address|
          |phone|string|Hotel phone number|

1. Create booking
   1. Overall

      |URL|Method|
      | :- | :- |
      |Local: http://localhost:8080/api/booking|POST|

   1. Request body

       |Field name|Type|Required/ Optional|Default value|Description|
       | :- | :- | :- | :- | :- |
       |customerId|integer|r||Customer id who books the hotel|
       |hotelId|integer|r||Hotel id is booked|
       |checkinTime|long|r||Check-in time|
       |checkoutTime|long|r||Check-out time|
       |totalAmount|double|r||Total amuont of bill|

   1. HTTP code

       |Code|Description|
       | :- | :- |
       |200|Success|
       |400|Invalid request|
       |500|Server error|

1. View booking details
   1. Overall

      |URL|Method|
      | :- | :- |
      |Local: /api/booking?id={id}|POST|

   1. Query param

       |Param|Type|Required/ Optional| Default value|Description|
       | :- | :- | :- |:-------------| :- |
       |id|integer|r|              |Location wants to search|

   1. HTTP code

       |Code|Description|
       | :- | :- |
       |200|Success|
       |400|Invalid request|

   1. Response body

    |Field name|Type|Description|
    | :- | :- | :- |
    |id|integer|Booking id|
    |customerId|integer|Customer id who books the hotel|
    |hotelName|string|Hotel name|
    |hotelAddress|string|Hotel address|
    |hotelPhone|string|Hotel phone|
    |checkinTime|long|Check-in time|
    |checkoutTime|long|Check-out time|
    |totalAmount|double|Total amount of bill|

