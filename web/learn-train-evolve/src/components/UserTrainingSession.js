import React, {useEffect} from 'react';
import {Button, Card, useTheme} from "@aws-amplify/ui-react";
import {useLocation} from "react-router-dom";
import LogTrainingSessionForm from "./LogTrainingSessionForm";
import {useNavigate} from 'react-router-dom';






const UserTrainingSession = ({userTrainingSession}) => {

    const {tokens} = useTheme();
    // allow component to read data passed from the state of the previous component
    const location = useLocation();

    const navigate = useNavigate();

    if(!userTrainingSession){
        userTrainingSession = location.state.userTrainingSession;
    }
    // set up to format the time correctly
    const [formattedDateTime, setFormattedDateTime] = React.useState("");


    // function for setting up the time
    useEffect(() => {
        const epochTime = userTrainingSession.timeAndDate;
        const date = new Date(epochTime * 1000); // Convert epoch time to milliseconds (*1000)
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

    }, [userTrainingSession.timeAndDate]);

    const handleCheckIn = () => {
        let email = userTrainingSession.email;
        console.log( "email getting passed is", email);
        let eventId = userTrainingSession.eventId;
        console.log("eventId getting passed is", eventId);
        navigate(`/train/${email}/signup/${eventId}`, {state: {userTrainingSession: userTrainingSession, email: email, eventId: eventId}});
        console.log("clicked");
    }


    return (
        <div >
                <Card variation = "elevated"
                        backgroundColor={tokens.colors.background.tertiary}
                >
                    <h3>Type: {userTrainingSession.type}</h3>
                    <h3>Coach: {userTrainingSession.coach}</h3>
                    <h4>{formattedDateTime}</h4>
                    <div>
                        {userTrainingSession.timeAndDate*1000 < Date.now() &&
                            <Button //on when form has not been filled out
                                variation="link"
                                onClick={handleCheckIn}
                            >Record My Training</Button>}
                        { userTrainingSession.timeAndDate*1000 > Date.now() &&
                            <Button //on when form has not been filled out
                                variation="disabled"
                            >Training Cannot Yet Be Recorded</Button>}

                    </div>
                </Card>
        </div>
    );

}

UserTrainingSession.displayName="UserTrainingSession";

export default UserTrainingSession;