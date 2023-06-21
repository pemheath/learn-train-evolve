import React, {useEffect, useState} from 'react';
import {Card, Button, Heading, Text, useTheme, Alert} from "@aws-amplify/ui-react";
import axios from "axios";
import {Auth} from "aws-amplify";
import {useNavigate} from "react-router-dom";






const SingleTrainingSession = ({ trainingSession }) => {

    const [formattedDateTime, setFormattedDateTime] = useState("");
    const {tokens} = useTheme();
    const [available, setAvailable] = useState(true);
    const [email, setEmail] = useState("");
    const [displayMessage, setDisplayMessage] = useState(false);
    const navigate = useNavigate();


    useEffect(() => {

        const epochTime = trainingSession.timeAndDate;
        const date = new Date(epochTime * 1000); // Convert epoch time to milliseconds (*1000)
        const readableDate = date.toLocaleString(); // Convert to readable format
        console.log("readable date is" + readableDate);

// Define the options for formatting the date and time, which will happen when timeAndDate changes
        const options = {
            weekday: 'long', // Display the full name of the weekday
            month: 'long', // Display the full name of the month
            day: 'numeric', // Display the day of the month
            hour: 'numeric', // Display the hour
            minute: 'numeric', // Display the minute
            hour12: true, // Use 12-hour format (am/pm)
        };
        setFormattedDateTime(new Intl.DateTimeFormat('en-US', options).format(date));

    }, [trainingSession.timeAndDate]);

    const getUserInfo = async ()=> {
        const cognitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = cognitoUser.signInUserSession.idToken.payload;
        console.log(email);
        setEmail(email);
        return { email, name };
    }

    const handleClick = async (eventId, timeAndDate, type, coach) => {
        console.log("Calling addToUserTrainingSessions with eventID: " + eventId + " and timeAndDate: " + timeAndDate + " and type: " + type + " and coach: " + coach);
        try {
            const api = axios.create({
                baseURL: `${process.env.REACT_APP_API_BASE_URL}`
            })
            const authenticatedEmail = (await getUserInfo()).email;
            console.log("Email is: " + authenticatedEmail);
            const result = await api.post(`user-training-sessions`, {
                    email: authenticatedEmail,
                    eventId: eventId,
                    timeAndDate: timeAndDate,
                    type: type,
                    coach: coach
                }
            );
            setAvailable(false);


        } catch (error) {
            console.log("error create user-training-session", error);

        }
    }
    const displayAlert = () => {
        setDisplayMessage(true);
    }
    const goToTrain = () => {
        navigate(`/train/${email}`, {state: {email: email}});
        console.log("clicked");
    }


    return ( formattedDateTime &&
                <Card variation = "elevated">
                    <Heading
                    >{trainingSession.type}</Heading>
                    <Heading
                    >Coach: {trainingSession.coach}</Heading>
                    <Text>{formattedDateTime}</Text>

                    {!available && (
                        <Alert variation="success"  heading="Success!" >
                            You have successfully signed up for this class!
                        </Alert>
                    )}

                    {available&& <Button
                        variation="primary"
                        onClick={() => handleClick(trainingSession.eventId, trainingSession.timeAndDate, trainingSession.type, trainingSession.coach)}
                    >Sign Up</Button>}
                    {!available&& <Button
                        variation="destructive"
                        onClick={displayAlert}
                    >Cancel Class</Button>}
                    {displayMessage &&<Alert
                        variation="error"
                        isDismissible={true}
                        hasIcon={true}
                        heading="Operation not supported"
                    >
                        Please see admin to cancel class.
                    </Alert>}
                    {!available&& <Button
                        variation="primary"
                        onClick={goToTrain}
                    >View My Schedule</Button>}

                </Card>

    )
}

SingleTrainingSession.displayName="SingleTrainingSession";

export default SingleTrainingSession;