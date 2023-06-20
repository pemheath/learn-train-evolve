import React, {useEffect, useState} from 'react';
import {Card, Button, Heading, Text, useTheme, View} from "@aws-amplify/ui-react";
import axios from "axios";
import {Auth} from "aws-amplify";





const SingleTrainingSession = ({ trainingSession }) => {

    const [formattedDateTime, setFormattedDateTime] = useState("");
    const [showMessage, setShowMessage] = useState(false);
    const {tokens} = useTheme();
    const [available, setAvailable] = useState(true);

    useEffect(() => {
        let timeoutId;
        if (showMessage) {
            timeoutId = setTimeout(() => {
                setShowMessage(false);
                // Reset the form here
            }, 2000);
        }
        return () => {
            clearTimeout(timeoutId);
        };
    }, [showMessage]);

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
            setShowMessage(true);
            setAvailable(false);

        } catch (error) {
            console.log("error create user-training-session", error);

        }
    }

    const displayMessage = () => {
        alert("Please see admin to cancel class!");
    }


    return ( formattedDateTime &&
                <Card variation = "elevated">
                    <Heading
                    >{trainingSession.type}</Heading>
                    <Heading
                    >Coach: {trainingSession.coach}</Heading>
                    <Text>{formattedDateTime}</Text>
                    {available&& <Button
                        variation="primary"
                        onClick={() => handleClick(trainingSession.eventId, trainingSession.timeAndDate, trainingSession.type, trainingSession.coach)}
                    >Sign Up</Button>}
                    {!available&& <Button
                        variation="destructive"
                        onClick={displayMessage}
                    >Cancel Class</Button>}
                    {showMessage && <div>
                        <View
                            backgroundColor={tokens.colors.green[20]}
                        > Successfully signed up for this session!
                        </View> </div>}
                </Card>

    )
}

SingleTrainingSession.displayName="SingleTrainingSession";

export default SingleTrainingSession;