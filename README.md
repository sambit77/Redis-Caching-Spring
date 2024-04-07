# Redis-Caching-Spring

## About Application: IF user makes  back to back api call within 5 seconds for a particular user Id the response will not be queried from database rather from cache memory <br>

### Step-1 Install and run Redis on your system (OSX) <br>

- `brew install redis` 
- `brew services start redis`

### Step-2 Run the spring application <br>

- `./gradlew build`
- `./gradlew bootrun`

### Step-3 Save a user to Mongo db database <br>

- `curl  -X POST \
  'http://localhost:8081/adduser' \
  --header 'Accept: */*' \
  --header 'User-Agent: Thunder Client (https://www.thunderclient.com)' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name" : "Sambit"
}'`

- This will create and save user "Sambit" to the database and return the userid as response

### Step-4 Get the user details based on id

- `http://localhost:8081/getbyid/1` : It will return user details for user id 1
- Response for back to back api call (<5 seconds>) for same user id will be returned from cache memory.