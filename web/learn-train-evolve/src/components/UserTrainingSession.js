import React, {useEffect, useState} from 'react';
import {Button, Card, SliderField, ThemeProvider} from "@aws-amplify/ui-react";
import {useLocation} from "react-router-dom";
import theme from "./Theme";





const UserTrainingSession = () => {
    // allow component to read data passed from the state of the previous component
    const location = useLocation();
    // set up to format the time correctly
    const [formattedDateTime, setFormattedDateTime] = React.useState("");
    // function for setting up the time
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

    const [showForm, setShowForm] = useState(false);

    const handleClick = () => {
        setShowForm(true);
    };

    return (
        <div>
            <ThemeProvider theme = {theme} >
        <Card variation = "elevated">
            <h3>Type: {location.state.userTrainingSession.type}</h3>
            <h3>Coach: {location.state.userTrainingSession.coach}</h3>
            <h4>{formattedDateTime}</h4>
            <div>
                {!showForm && (
                    <Button
                        variation="primary"
                        onClick={() => handleClick()}
                    >Record Training</Button>
                )}
                {showForm && (
                    <form>
                        <SliderField
                            label="How did you enjoy the technique?"
                            type="techniqueEnjoyment"
                            min={1}
                            max={7}
                            step={1}
                            defaultValue={4}
                        />

                        <SliderField
                            label="How did you perform today?"
                            type="performanceRating"
                            min={1}
                            max={7}
                            step={1}
                            defaultValue={4}
                        />
                        <SliderField
                            label="How intense was your training?"
                            type="techniqueEnjoyment"
                            min={1}
                            max={10}
                            defaultValue={5}
                        />
                        );
                        {/* Additional form fields go here */}

                        <button type="submit">Submit</button>
                    </form>
                )}
            </div>
    </Card>
                </ThemeProvider>
        </div>
    );
}

export default UserTrainingSession;