# Appinventive
Crud Application

Following are the Curl Requests

//Curl Request to create Movie

curl --location --request POST 'localhost:5000/api/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":10,
    "title":"3 Idiot",
    "category":"motivated",
    "starRating":4.5
}'

// Curl Request to get Movie by Id

curl --location --request GET 'localhost:5000/api/getMovie/2'

//Curl Request for deleting given id movie 

curl --location --request DELETE 'localhost:5000/api/deleteById/1'

//Curl Request to update Movie

curl --location --request PUT 'localhost:5000/api/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":5,
    "title":"Robot",
    "category":"action",
    "starRating":4.8
}'

//Curl Request to get List of Movies

curl --location --request GET 'localhost:5000/api/getMovies'
