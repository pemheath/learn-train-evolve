import React, {useEffect} from 'react';
import {Card, Collection, useTheme} from "@aws-amplify/ui-react";
import { ImFrustrated, ImSad, ImNeutral, ImSmile,  ImHappy  } from "react-icons/im";


const UpdatedUserTrainingSession = ({userTrainingSession}) => {

    console.log(userTrainingSession.timeAndDate);

    const{tokens} = useTheme();

    const [formattedDateTime, setFormattedDateTime] = React.useState("");

    // function for setting up the time
    useEffect(() => {
        const epochTime = userTrainingSession.timeAndDate;
        const date = new Date(epochTime * 1000); // Convert epoch time to milliseconds (*1000)
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

    const translatePerformance = (performanceRating) => {
        switch (performanceRating) {
            case "1":
                return <ImFrustrated/>;
            case "2":
                return <ImSad/>;
            case "3":
                return <ImNeutral/>;
            case "4":
                return <ImSmile/>;
            case "5":
                return <ImHappy/>;
            default:
                return "Not rated";
        }

    }

    const translateTechniqueEnjoyment = (techniqueEnjoyment) => {
        switch (techniqueEnjoyment) {
            case 1:
                return "Hated it.";
            case 2:
                return "Not for me";
            case 3:
                return "I see the utility";
            case 4:
                return "Neutral";
            case 5:
                return "I liked it.";
            case 6:
                return "I loved it.";
            case 7:
                return "One of my favorites.";

            default:
                return "Not rated";
        }
    }


    return (
        <div>
                <Card variation = "elevated">
                    <h3>Type: {userTrainingSession.type}</h3>
                    <h3>Coach: {userTrainingSession.coach}</h3>
                    <h4>{formattedDateTime}</h4>
                    <p>My performance was {translatePerformance(userTrainingSession.performanceRating)}</p>
                    <p>Intensity: {userTrainingSession.intensity} %</p>
                    <p>Technique: {translateTechniqueEnjoyment(userTrainingSession.techniqueEnjoyment)}</p>
                    {userTrainingSession.note && <p>Notes: {userTrainingSession.note}</p>}
                    {userTrainingSession.goal && <p>Goal: {userTrainingSession.goal}</p>}
                    <Collection
                        type = "list"
                        backgroundColor={tokens.colors.white}
                        items={userTrainingSession.tags}
                        gap = "1.rem"
                    >
                        {(item, index) => (
                            <Card
                                key={index}
                                variation = "elevated"
                                tag={item}
                            >{item}</Card>

                        )}
                    </Collection>
                </Card>
        </div>
    );

}

export default UpdatedUserTrainingSession;