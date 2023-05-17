# Design Document

## Learn Train Evolve: A Modern Jiu Jitsu Management Software

## 1. Problem Statement

Brazilian Jiu Jitsu (BJJ) is growing in popularity with academies opening to meet the surging demand. The sport of jiu jitsu is unique in its combined mental and physical complexity. Likened to "chess with your body", jiu jitsu requires practitioners to understand movements, attacks and defenses from common positions. Upon this basic framework (how pieces move) come combinations, sequences, strategy and tactics. However, knowing the move intellectually is only half of the game, because your body has to be able to execute the move with precise technique and timing, requiring coordinated movements that become muscle memory with routine practice. The complexity of the sport presents a problem: *how can practitioners manage and optimize their learning and training process?*

 
Jiu jitsu practitioners commonly track what they are learning, how it fits into their existing knowledge base, and what they need to learn next. At more beginner levels, this tracking is primarily executed by the curriculum, but as students become more intermediate, they must take more ownership of their development. Students use many systems for tracking their progress, including physical notebooks, a note on a smartphone, excel sheets, word docs, etc. **Students would benefit from an integrated system where they can organize their training notes, set goals, and track progress on a platform that is integrated with their academy, their gym schedule and curriculum, and their teammates+coaches.** 

Learn Train Evolve provides athletes and coaches an integrated platform to track and manage training, technique development, goals, and coaching. With two types of users (students + administrators), admins can view and manage their students + upload resource based content, while students can manage their own training plan and collect notes and resources in one convenient location. 

## 2. Top Questions to Resolve in Review

1. Would using Amazon Athena in conjunction with DynamoDB facilitate me to provide the kind of data visualization and reports this service will provide?
2. I will need to preload a good amount of data (goals, "why" statements) -- is there an efficient way to preload dynamodb databases? Is a json doc the fastest way? I would love to store the data in excel and somehow convert that to a format DynamoDB would use. Via Json? 
3. I need calendar integration for class schedules. What integration would I use here? Is there a google calendar API to integrate this in? I need to explore these options. 
4. I would like the administrator to readily update information: would using Wordpress as a content management system and integrate it into my project as a headless wordpress be an option for this?

## 3. Vocabulary and User Stories

- User: A student of the jiu-jitsu academy
- Student: may be used synonymously with user, especially when talking about students vis-a-vi the administrator or other users.
- Administrator: A coach/owner of the academy who will have administrative access to the site, including the ability to upload content, read access to parts of students profiles, CRUD activities for "users".
-Academy: The school where the users train. 

### User stories by category

- B: 
	**Basic Adminstrator Functionality**
	* UC. As an administrator, I want to add new students to my academy. (B)
	* UC. As an administrator, I want to remove students from my academy. (B)
	* UC. As an administrator, I want to update information about one of my students. (B)
- T: 
	**functionality associated with TRAIN component**
	* UC. As an administrator, I want to view the training history for any of my students. (T)
	* UC. As an administrator, I want to see who has signed up for a class (present moment to one week out) or who has attended a class (past class). (T)
	* UC. As a user, I want to be able to sign up for a class. (T)
	* UC. As a user, I want to be able to make notes about a class attached to that class object. (T)
	* UC. As a user, I want to be able to view my past training, and sort my training by different variables. (T)
	* UC. As a user, I want to be able to interact with my academy community by posting messages in a social channel. (T) (stretch)
- L: 
	**functionality associated with LEARN component**
	* UC. As an administrator, I want to update content on the website, like add photos, post events, and upload video resources. (L)
	* UC. As a user, I want to view resources to enable my learning. (L)
	* UC. As a user, I want to view video of the techniques taught that week. (L)
	* UC. As a user, I want to create notes about techniques I am learning. (L)
- E: 
	**functionality associated with EVOLVE component**
	* UC. As a user, I want to be able to select a goal from a list of suggestions. (E)
	* UC. As a user, I want to be able to create a new goal. (E)
	* UC. As a user, I want to be able to update a goal, including making notes or marking the goal as completed.  (E)
	* UC. As a user, I want to be ablve to view my completed, ongoing, and future goals. (E)


## 4. Project Scope


### 4.1. In Scope

In this design I will solve the problem of jiu jitsu practitioners needing the ability to organize information about their training. For this first iteration of the project I will focus on the *TRAIN* thread of "Learn, Train, Evolve". Users will be able to sign up for training sessions, view their training sessions, sort their training sessions, and add notes to their training sessions. 



### 4.2. Out of Scope

- To manage the scope, this initial iteration will not yet support the "learn" and "evolve" functionality. Those will come in phase 2 and phase 3.
- This initial product will not support creating a user that already has training data. Any newly created user will start with zero data regarding training history. 
- This intial product will not integrate with any other fitness apps (Whoop, Apple Watch, etc) and it will only be a web based application (no smartphone apps, for example).
- This product will only support a single location Academy with adult programs only. 
- This product will not allow athletes to track and plan for competition: the focus will only be on training at the academy. A compete thread can come in a future phase.


# 5. Proposed Architecture Overview

An important element of this design is that it is separated into three key components: learn, train, and evolve. 
- The LEARN component will cover use cases related to notes on training and accessing resources. 
- The TRAIN component will cover use cases related to scheduling trainign sessions, taking notes on training sessions, and viewing a history of training sessions (data analytics will be available such as volume by week, by type, by day, etc).
- The EVLOLVE component will cover use cases related to goal setting and reflection. 

In this way, concerns are separated and the project can be developed incrementally. Phase 1 (this phase) will tackle the TRAIN component.


User and TrainingSession data will be stored in DynamoDB. 
On the backend, the application will receive a request, and an activity class will handle the request. Handling the request will entail parsing the request, interacting with a DAO, and creating/returning a response object. The DAO will serve as a class to manage all data access through DynamoDB. 

The front end will accept the user input and create the request to pass to the application. 

The front end will receive the response object and display the output accordingly. 

Each thread "LEARN, TRAIN, EVOLVE" will have representation on the application home page, and each will have their own page where full functionality of that element of the design lives. For example, a user can see their upcoming training on the home page, but to view training, make notes, and sort training, they will navigate to the TRAIN page. 



# 6. API

## 6.1. Public Models

```

{
	// UserModel

	String id; --> unique identifier for each member
	String email;
	String firstName;
	String lastName;
	String motivationalWhy;
	String membership;
	String rank;

	} 
```

```

{
	// TrainingSessionModel

	String id; (id for user)
	String sessionId;
	String type;
	Note note;
	Set<String> tags;

	}

```

```

{
	// Goal

	String id;
	int goalNumber;
	String goalTitle;
	LocalDate dateCreated;
	String goalContent;
	String goalStatus;
	Set<String> taskList;
	Set<String> tags;

}
```

```

{

	//Note

	String id;
	int noteNumber;
	String noteTitle;
	String noteContent;
	LocalDate dateCreated;
	Set<String> tags;
	String sessionId; (corresponding training session if applicable)


	}
```


## API ENDPOINTS

See [API here](openapi.json) 

# 7. Tables

Phase one will encomopass the User and Train tables. Both will be stored in DynamoDB. 

7.1 `Users`

```

id // partion key, S
email // S
firstName // S
lastName // S
motivationalWhy // S
membership // S
rank // S

```

7.2 `TrainingSessions`

```
id // partion key, S
sessionId // sort key, S
type // S
note // M
tags // SS

```

7.3 `Notes`

```

id // partion key, S
noteNumber // sort key, N
noteTitle // S
noteContent // S
dateCreated // S
tags // SS
sessionId // S

```



# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_




