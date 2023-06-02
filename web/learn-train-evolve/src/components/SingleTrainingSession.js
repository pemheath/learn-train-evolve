import React, {useEffect} from 'react';
import {Card, Button} from "@aws-amplify/ui-react";

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





    return ( formattedDateTime &&
        <Card variation = "elevated">
            <h2>{trainingSession.type}</h2>
            <h3>{trainingSession.coach}</h3>
            <p>{formattedDateTime}</p>
            <Button
                variation="primary"
                onClick={() => alert('Functionality pending!')}
            >Sign Up</Button>
        </Card>
    )
}

export default SingleTrainingSession;