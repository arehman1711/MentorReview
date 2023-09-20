
# Mentor Review 


The Mentor Review API is a comprehensive backend system designed to empower mentor-student interactions, facilitate reviews, enable mentor recommendations (Letters of Appreciation), and manage mentorship within a web application. This RESTful API offers a wide range of endpoints to support various features, enhancing the overall mentorship experience.





## REST API
### User Management
### Create User

```http
  POST /api/users

```


#### Request Body Example:

```http
  {
  "name": "Shayan"
  }
```
#### Response Example:

```http
  {
  "id": 1,
  "name": "Shayan"
  }
```
### Mentor Management
#### Create Mentor

```http
  POST /api/mentors/create

```



#### Request Body Example:

```http
  {
  "name": "Abdur Rehman"
  }
```
#### Response Example:

```http
 {
    "id": 1,
    "name": "Abdur Rehman",
    "overallRating": 0.0,
    "reviews": null
}
```
### Review Management
#### Submit Review for a mentor(user should be able to give a review to the mentor (in 50 words).


```http
  POST /api/review-mentor

```
| Request Parameters  | Description                       |
 :------- | :-------------------------------- |
  | `userId` (Long) |  ID of the user submitting the review. |
   | `mentorId` (long) | ID of the mentor being reviewed |
   | `review` (String) | Review content. |


#### Response Example (Success):

```http
 "Review submitted successfully"
```
#### Response Example (Error):

```http
"Failed to submit review"

```
### Recommendation Management
#### Create a Student recommendation(Mentor should be able to recommend a student i.e they can give them a letter of recommendation which should be shareable to everyone via link. i.e there should be an api which anyone can hit and should be able to see the letter of recommendation).


```http
  POST /api/recommendations

```
| Request Parameters  | Description                       |
 :------- | :-------------------------------- |
  | `userId` (Long) |  ID of the mentor. |
   | `mentorId` (long) | ID of the user creating the recommendation.|
   | `recommendationText` (String) | Recommendation content. |


#### Response Example (Success):

```http
"Recommendation created successfully"
```
#### Response Example (Error):

```http
"Failed to create recommendation"
```
### Retrieve Recommendation
####  Retrieve a recommendation by its unique shareable link.


```http
  GET /api/recommendations/{uuid}

```
| Request Parameters  | Description                       |
 :------- | :-------------------------------- |
  | `{uuid}` (String)|  Unique shareable link. |

   

#### Response Example (Success):

```http
"An exceptional student, demonstrating remarkable dedication and academic excellence.."

```
#### Response Example (Error):

```http
"Recommendation not found for the provided link"

```
### Rating Management
####  Rate a mentor(user should be able to give a rating to a mentor out of 5 stars. And after the rating the overall rating of the mentor should also change).

```http
  GET /api/rate-mentor/{mentorId}

```
| Request Parameters  | Description                       |
 :------- | :-------------------------------- |
  | `{mentorId}` (Long)|  ID of the mentor being rated. |

   

#### Response Example (Success):

```http
{
  "rating": 4
}

```
#### Response Example (Error):

```http
"Failed to rate mentor"
```
### Mentor Details
####  Get mentors by selecting ratings (either1,2,3,4,5) and all the mentors along with their reviews


```http
  GET /api/get-mentor-details/byrating/{rating}

```
| Request Parameters  | Description                       |
 :------- | :-------------------------------- |
  | `{rating}` (int)|  The rating to filter mentors. |

   

#### Response Example (Success):

```http
[
  {
    "id": 1,
    "name": "Abdur Rehman",
    "overallRating": 4.0,
    "reviews": [
      {
        "id": 1,
        "reviewText": "good",
        "user": {
          "id": 1,
          "name": "shayan"
        }
      },
      {
        "id": 2,
        "reviewText": "nice",
        "user": {
          "id": 2,
          "name": "Khushhal"
        }
      }
    ]
  }
]


```
#### Response Example (Error):

```http
 "No mentors found with rating 5"

```


## Installation
 To run this project locally, follow these steps:

```bash
1. Clone the repository.
2. Install dependencies.
3. Configure database and application properties.
4. Run the application.
```
    
## Features


- User Interaction: Users can create accounts and participate in the mentorship ecosystem.

- Mentor Management: Mentor profiles can be created, each detailing the mentor's expertise.

- Review System: Users can submit reviews for mentors, providing valuable feedback.

- Recommendation Functionality: Mentors have the capability to recommend students and generate Letters of Appreciation



## Tech Stack
**Language:** Java

**Framework:** Spring Boot

**Database:** H2

**Dependencies:** JPA (Java Persistence API), Lombok, SLF4J (Simple Logging Facade for Java), Spring Boot DevTools, Spring Boot Validation.

**API Technology:** RESTful API

**IDE:** IntelliJ IDEA

**Version Control:** Git, GitHub

**API Testing:** Postman