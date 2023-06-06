import React, {useEffect} from 'react';
import {Button, Card} from "@aws-amplify/ui-react";
import {useLocation} from "react-router-dom";
import {Auth} from "aws-amplify";



const UserTrainingSession = () => {

    const location = useLocation();
    console.log(location.state);
    return (
        <div>
        <Card variation = "elevated">
            <h1>Hello {location.state.name}</h1>
            <h3>Type: {location.state.userTrainingSession.type}</h3>
            <h3>Coach: {location.state.userTrainingSession.coach}</h3>
            <h3>{location.state.userTrainingSession.timeAndDate}</h3>
            <Button
                variation="primary"
                onClick={() => alert("Functionality pending!")}
            >Record Training</Button>
    </Card>
        </div>
    );

}

export default UserTrainingSession;