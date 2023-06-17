import React from "react";
import '../styles.css';


import {Outlet, useLocation} from "react-router-dom";
import UserTrainingSession from "./UserTrainingSession";
import ListOfUserTrainingSessions from "./ListOfUserTrainingSessions";
import {ScrollView, useTheme} from "@aws-amplify/ui-react";
import App from "../App";


function TrainComponent() {

    // allow component to read data passed from the state of the previous component
    const location = useLocation();
    const{tokens} = useTheme();
    console.log("in the train component");

    return(
        <div>

            <ScrollView
                height = "300px"
                width = "400px"
                padding = "1rem"
                backgroundColor={tokens.colors.brand.primary[60]}>
            <UserTrainingSession
            userTrainingSession={location.state.userTrainingSession}
            />
        <div>
            <ListOfUserTrainingSessions/>
        </div>
            </ScrollView>
        </div>
    );

}
TrainComponent.displayName="TrainComponent";

export default TrainComponent;