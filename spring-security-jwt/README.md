// Steps To test Appolication


URI: http://localhost:8080/authenticate
Method: Post
Request:
{
    "username":"foo",
    "password":"foo"

}

Response:
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE1OTU4MDU5NTQsImlhdCI6MTU5NTc2OTk1NH0.Ol0-HM1VzWpxde7A_pSsCatSZ4tXbE52PN3EqBT4NMA"
}