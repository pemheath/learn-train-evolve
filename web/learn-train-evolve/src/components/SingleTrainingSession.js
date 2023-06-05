import React, {useEffect} from 'react';
import {Card, Button} from "@aws-amplify/ui-react";
import axios from "axios";
import {Auth} from "aws-amplify";

const SingleTrainingSession = ({ trainingSession }) => {

    const theme = {
        name: 'card-theme',
        tokens: {
            components: {
                card: {
                    // You can reference other tokens
                    backgroundColor: { value: '{colors.background.success}' },
                    borderRadius: { value: '{radii.large}' },
                    padding: { value: '{space.xl}' },

                    // Variations
                    outlined: {
                        // Or use explicit values
                        borderWidth: { value: '10px' },
                        backgroundColor: { value: '{colors.background.warning}' },
                    },
                    elevated: {
                        backgroundColor: { value: '{colors.background.info}' },
                        boxShadow: { value: '{shadows.large}' },
                    },
                },
            },
        },
    };



    const [formattedDateTime, setFormattedDateTime] = React.useState("");

    useEffect(() => {

        const isoDateTimeString = trainingSession.timeAndDate;

        const date = new Date(isoDateTimeString); // create date object from string

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
        const congnitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = congnitoUser.signInUserSession.idToken.payload;
        return { email, name };
    }

    const addToUserTrainingSessions = async (eventId, timeAndDate, type, coach) => {
        console.log("Calling addToUserTrainingSessions with eventID: " + eventId + " and timeAndDate: " + timeAndDate + " and type: " + type + " and coach: " + coach);
        try {
            const api = axios.create({
                baseURL: 'http://localhost:3000'
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
            const userTrainingSession = result.data;
            console.log(userTrainingSession);
        } catch (error) {
            console.log("error create user-training-session", error);
        }
    }

    return ( formattedDateTime &&
        <Card variation = "elevated">
            <h2>{trainingSession.type}</h2>
            <h3>{trainingSession.coach}</h3>
            <p>{formattedDateTime}</p>
            <Button
                variation="primary"
                onClick={() => addToUserTrainingSessions(trainingSession.eventId, trainingSession.timeAndDate, trainingSession.type, trainingSession.coach)}
            >Sign Up</Button>
        </Card>
    )
}

export default SingleTrainingSession;