# Machine Learning API on Cloud Build

### 1. Write the Flask server app using Python
Save the model in any model format in the same directory as main.py
Load the model and write Flask code in main.py
Test the Flask server locally by running main.py with postman.
### 2. Setup On Google Cloud
Enable Cloud Run and Cloud Build API
### 3. Install Google Cloud SDK
https://cloud.google.com/sdk/docs/install
### 4. Dockerfile, requirements.txt, .dockerignore
https://cloud.google.com/run/docs/quickstarts/build-and-deploy#containerizing
### 5. Cloud build & deploy
### 5.1. Build & Deploy manually 
#### a. First you make a Cloud Source Repositories
![image](https://github.com/AliceMochi/leckerscaptone12/assets/118159857/42b22089-39fe-487f-8e72-e503b01df7f7)
#### b. Go to Cloud Run and then create service
![image](https://github.com/AliceMochi/leckerscaptone12/assets/118159857/fbfef0af-6584-4ae2-886e-bb5d5c85cfe0)
#### c. Chose the continuously deploy and chose cloud source repositories
![image](https://github.com/AliceMochi/leckerscaptone12/assets/118159857/d530fab4-b0d6-40fb-ba8c-ac7aa77d7538)
#### d. Please chose the the star * with any branch for branch and dockerfile
![image](https://github.com/AliceMochi/leckerscaptone12/assets/118159857/36892f01-34aa-4e17-98e9-db87e2053a0d)
#### e. You can customasation the evirontment as needed and when done, you can click create.
![image](https://github.com/AliceMochi/leckerscaptone12/assets/118159857/1fab3c6c-b18c-41ea-9488-65524cf1dde5)
### 5.2. Build & Deploy with cloud shell
```
gcloud builds submit --tag gcr.io/capstone-lecker/index
```
```

gcloud run deploy --image gcr.io/capstone-lecker/index --platform managed
```

### Test
You can test local with postman before deploy it to the cloud run. and you also can test it in postman again after you deploy to cloud run

