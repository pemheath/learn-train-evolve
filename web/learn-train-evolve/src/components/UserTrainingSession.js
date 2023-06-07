import React, {useEffect} from 'react';
import {Button, Card, ThemeProvider} from "@aws-amplify/ui-react";
import {useLocation} from "react-router-dom";
import theme from "./Theme";




const UserTrainingSession = () => {

    const location = useLocation();

    const [formattedDateTime, setFormattedDateTime] = React.useState("");
    useEffect(() => {
        const epochTime = location.state.userTrainingSession.timeAndDate;
        const date = new Date(epochTime * 1000); // Convert epoch time to milliseconds (*1000)
        const readableDate = date.toLocaleString(); // Convert to readable format
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

    }, [location.state.userTrainingSession.timeAndDate]);

    return (
        <div>
            <ThemeProvider theme = {theme} >
        <Card variation = "elevated">
            <h3>Type: {location.state.userTrainingSession.type}</h3>
            <h3>Coach: {location.state.userTrainingSession.coach}</h3>
            <h4>{formattedDateTime}</h4>
            <Button
                variation="primary"
                onClick={() => alert("Functionality pending!")}
            >Record Training</Button>
    </Card>
                </ThemeProvider>
        </div>
    );
}

export default UserTrainingSession;