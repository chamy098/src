# SRC challenge

## Description
 - Two independent deployable services.
 - Single database.
 - Dockerized.

## Run
 - `docker-compose up` or `docker-compose up -d` to run in background.

## Services
-  http://localhost:8080/api/actor - Actor-service
-  http://localhost:8081/api/movie - Movie-service

## Endpoints - Actor-service
    - GET /getAllActors - Get all actors
    - GET /getActorsByPage/{size}/{page} - Get actors by page. Size is the number of actors per page and page is the page number. Page starts at 0.
    - GET /getById/{id} - Get actor by id
    - POST /create - Create actor
    - PUT /update - Update actor
    - DELETE /delete/{id} - Delete actor by id

## Endpoints - Movie-service
    - GET /getAllMovies - Get all movies
    - GET /getMoviesByPage/{size}/{page} - Get movies by page. Size is the number of movies per page and page is the page number. Page starts at 0.
    - GET /search/{word} - Search movies by word    
    - GET /getById/{imdbID} - Get movie by imdbID
    - POST /create - Create movie
    - PUT /update - Update movie
    - DELETE /delete/{imdbID} - Delete movie by imdbID

## Datamodel
    - Actor
        - id // If id is not provided, the actor will be created
        - firstName (Required)
        - lastName (Required)
        - dateOfBirth
        - movies
    - Movie
        - imdbID
        - title (Required)
        - releaseYear (Required)
        - description
        - actors

## Example create movie
- POST http://localhost:8081/api/movie/create
  - Body:
    ```json
    {
        "title": "Carmencita",
        "releaseYear": 1894,
        "description": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
        "actors": [
            {
                "id": 1,
                "firstName": "Carmencita",
                "lastName": "Unknown",
                "dateOfBirth": "1868-11-00"
            }
        ]
    }
## Example update movie
- PUT http://localhost:8081/api/movie/update
    - Body:
      ```json
      {
          "imdbID": "1",
          "title": "Carmencita 2",
          "releaseYear": 1894,
          "description": "Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.",
          "actors": [
              {
                  "id": 1,
                  "firstName": "Carmencita",
                  "lastName": "Unknown",
                  "dateOfBirth": "1868-11-00"
              }
          ]
      }    
## Example create actor
- POST http://localhost:8080/api/actor/create
  - Body:
    ```json
    {
     "firstName": "Elijah",
     "lastName": "Wood",
     "dateOfBirth": "1981-01-28",
     "movies": [
       {
        "title": "The Lord of the Rings: The Fellowship of the Ring",
        "releaseYear": 2001,
        "description": "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron."
      }
     ]
    } 

## Example update actor
- PUT http://localhost:8080/api/actor/update
    - Body:
      ```json
      {
       "id": 1,
       "firstName": "Elijah",
       "lastName": "Wood",
       "dateOfBirth": "1981-01-28",
       "movies": [
         {
          "imdbID": 1,
          "title": "The Lord of the Rings: The Fellowship of the Ring",
          "releaseYear": 2001,
          "description": "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron."
        },
        {
          "title": "The Lord of the Rings: The Two Towers",
          "releaseYear": 2002,
          "description": "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard."
        }
       ]
      } 
