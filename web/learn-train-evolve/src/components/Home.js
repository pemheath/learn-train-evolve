import React from "react";
import '../styles.css';
import TrainingSessions from "./TrainingSessions";
import Goals from "./Goals";
import Lessons from "./Lessons";
import {Auth} from "aws-amplify";

import {
    Collection, View
} from '@aws-amplify/ui-react';
import App from "../App";



export const Home = () => {

    const[loggedIn, setLoggedIn] = React.useState(false);
    const isUserLoggedIn = async ()=> {
        try {
            await Auth.currentAuthenticatedUser();
            console.log('user is logged in');
            setLoggedIn(true);
        }
        catch(e) {
            setLoggedIn(false);
        }
    }
    const items = [
        <Lessons/>,
        <TrainingSessions/>,
        <Goals/>
    ];

    return (
        ({loggedIn}&&

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
        </Collection>)
);
};
Home.displayName="Home";
export default Home;