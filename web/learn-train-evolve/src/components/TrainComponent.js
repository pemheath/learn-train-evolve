import React from "react";
import '../styles.css';


import {Outlet, useLocation} from "react-router-dom";
import UserTrainingSession from "./UserTrainingSession";


function TrainComponent() {

    // allow component to read data passed from the state of the previous component
    const location = useLocation();

    return(
        <div>
            <UserTrainingSession
            userTrainingSession={location.state.userTrainingSession}
            />
        <div id="detail">
            <Outlet/>
        </div>
        </div>
    );

}

export default TrainComponent;