import React from "react";
import '../styles.css';
import TrainingSessions from "./TrainingSessions";
import Goals from "./Goals";
import Lessons from "./Lessons";
import NavMenu from "./NavMenu";

import {
     Grid, Card
} from '@aws-amplify/ui-react';



/**
 * Home is the component for the index path and displays the home page

 */

export const Home = ({cognitoUser}) => {

    console.log("cognitoUser is ", cognitoUser);
    console.log("email is", cognitoUser.attributes.email);
    const email = cognitoUser.attributes.email;


    const items = [
        <NavMenu
            email={email}
        />,
        <TrainingSessions
            email={email}
        />,
        <Goals/>,
        <Lessons/>
    ];

    return (
        <Grid
            columnGap="0.5rem"
            rowGap="0.5rem"
            templateColumns="1fr 3fr 3fr"
            templateRows="2fr 2fr 3fr"
        >
            <Card
            columnStart="1"
            columnEnd="2"
            >
                {items[0]}
            </Card>
            <Card
                columnStart="2"
                columnEnd="3"
            >
                {items[1]}
            </Card>
            <Card
                columnStart="3"
                columnEnd="-1"
            >
                {items[2]}
            </Card>
            <Card
                columnStart="2"
                columnEnd="7"
            >
                {items[3]}
            </Card>
        </Grid>


);
};
Home.displayName="Home";
export default Home;