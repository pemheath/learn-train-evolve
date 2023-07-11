# learn-train-evolve

## Learn Train Evolve: A Modern Jiu Jitsu Management Software

Check out the project! It is a work in progress. Here is the link: https://d2jhl9mq8vpmme.cloudfront.net/

## 1. About this project:

Brazilian Jiu Jitsu (BJJ) is growing in popularity with academies opening to meet the surging demand. The sport of jiu jitsu is unique in its combined mental and physical complexity. Likened to "chess with your body", jiu jitsu requires practitioners to understand movements, attacks and defenses from common positions. Upon this basic framework (how pieces move) come combinations, sequences, strategy and tactics. However, knowing the move intellectually is only half of the game, because your body has to be able to execute the move with precise technique and timing, requiring coordinated movements that become muscle memory with routine practice. The complexity of the sport presents a problem: *how can practitioners manage and optimize their learning and training process?*

Jiu jitsu practitioners commonly track what they are learning, how it fits into their existing knowledge base, and what they need to learn next. At more beginner levels, this tracking is primarily executed by the curriculum, but as students become more intermediate, they must take more ownership of their development. Students use many systems for tracking their progress, including physical notebooks, a note on a smartphone, excel sheets, word docs, etc. **Students would benefit from an integrated system where they can organize their training notes, set goals, and track progress on a platform that is integrated with their academy, their gym schedule and curriculum, and their teammates+coaches.** 

Learn Train Evolve provides athletes an integrated platform to track and manage training, technique development, goals, and coaching. 

The app is composed of three primary components: 
 ### As of June 25, 2023, the TRAIN component is the most developed. The entire application is in progress.

 _Vocabulary_
 - User: A student of the jiu-jitsu academy
- Student: may be used synonymously with user, especially when talking about students vis-a-vi the administrator or other users.
- Administrator: A coach/owner of the academy who will have administrative access to the site, including the ability to upload content, read access to parts of students profiles, CRUD activities for "users".
- Academy: The school where the users train. 

### Learn 
-  A platform for students to manage and collect resources, view notes about training, add notes to resources, and search past notes so that their learning is documented and accessible as they develop

### Train 
- - The academy owner can use a google calendar to schedule classes and sync those classes to a table in dynamodb
- - Students can view upcoming classes, sign up for them, and check in through the app
- - After checking in, students can log information about their training, including notes, affiliated goals, and technique tags that will flag this training when the athlete searches that tag in the future.
- - Students can see basic data visualizations to view trends in theit training type and frequency
 
  ### Evolve
  - This component allows the user to set and manage goals
  - Setting short, medium, and long term goals is essential to progressing in learning and mastering anything new
  - The app will provide functionality for selecting suggested goals and setting custom goals, trackign those goals, and viewing past goals
 

## 2. Architecture and Technologies

This project utilizes a React-based frontend application that interacts with a Java-based backend. The backend is implemented using AWS Lambda functions, and the data is stored in DynamoDB.
The project utilizes AWS Cognito for user management and integrates with the Google Calendar API to sync the schedule with the rest of the application.


## 3. API

The API can be found in the openapi.json file in the resources folder: https://github.com/pemheath/learn-train-evolve/new/main?readme=1#:~:text=log4j2.properties-,openapi,-.json

## 3.1. Public Models

```
{
	// UserModel -- managed in Cognito
	
	String email; --> unique identifier for each member
	String name
	String membership;
	String rank;
	String calId --> only for administrators who can sync training sessions with google
	} 
```

```
{
	// TrainingSessionModel

	String eventId; (id for session/event resource)
	long timeAndDate
	String type;
	String coach
	Boolean isCancelled;
	}
```

```
{
	// UserTrainingSessionModel

	String email; (id for user)
	String eventId; (id for session/event resource)
	long timeAndDate;
	String type;
	int intensityRating;
	int techniqueEnjoyment;
	int performanceRating;
	String note;
	String goal;
	Set<String> tags;
	Boolean attended;
	}
```


```
{
	// Goal

	String email;
	String goalTitle;
	long dateCreated;
	Set<String> taskList;
	Set<String> completedTaskList;

}
```

```
{
	//Note

	String email;
	String noteContent;
	long dateCreated;
	Set<String> tags;
	String eventId; (corresponding training session if applicable)
	}
```

See [API documentation](openapi.json)

See [SyncTrainingSessions endpoint sequence diagram](SyncTrainingSessionsSequenceDiagram.puml)
See [GetTrainingSession endpoint sequence diagram](GetTrainingSessionSequenceDiagram.puml)
See [UpdateUserTrainingSessionSequenceDiagram endpoint sequence diagram](UpdateUserTrainingSessionSequenceDiagram.puml)
See [UML diagram here](LearnTrainEvolve.puml)

## 4. Tables in DynamoDB

Phase one will encompass the TrainingSessions and UserTrainingSessions tables stored in DynamoDB. 



4.1 `TrainingSessions`

```
eventId // primary key, S
timeAndDate // N 
coach // S
type // S
isCancelled // BOOL

```

4.2 `UserTrainingSessions`

```
email // partion key, S
eventId // sort key, S
type // S
coach // S
timeAndDate // N
intensityRating // N
techniqueEnjoyment // N
performanceRating // N
note // S
goal // S
tags // SS
attended // BOOL

```

4.3 `Notes`

```

email // partion key, S
noteContent // S
dateCreated // N
tags // SS
eventId // S

```
4.4 `Goals`

```

email // partion key, S
goalTitle // S
taskList // SS
completedTaskList // SS
completed // BOOL

```



### Known Bugs!

- When a user has a specia character in their email, put and post requests leave out the special character, and therefore their data is not up to date.
- Hitting "refresh" when cloudfront is hosting the website leads to an error.
- If you are not on the main page when you sign out, when the next person signs in they will be directed to where you signed out from (if signing in from the same computer). Cognito redirect needs to be updated.
