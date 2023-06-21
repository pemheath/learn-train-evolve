import React from "react";
import '../styles.css';
import TrainingSessions from "./TrainingSessions";
import Goals from "./Goals";
import Lessons from "./Lessons";

import {
    Collection, View
} from '@aws-amplify/ui-react';


/**
 * Home is the component for the index path and displays the home page

 */

export const Home = ({cognitoUser}) => {

    console.log("cognitoUser is ", cognitoUser);
    console.log("email is", cognitoUser.attributes.email);
    const email = cognitoUser.attributes.email;


    const items = [
        <Lessons/>,
        <TrainingSessions
            email={email}
        />,
        <Goals/>
    ];

    return (

        <Collection
            type="grid"
            items={items}
            templateColumns="1fr 1fr 1fr"

        >
            {(item, index) => {
                return (
                    <View
                        key={index}
                        column={index + 1}
                    >
                        {item}
                    </View>
                );
            }}
        </Collection>
);
};
Home.displayName="Home";
export default Home;