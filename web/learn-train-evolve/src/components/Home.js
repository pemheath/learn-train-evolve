import React from "react";
import '../styles.css';
import TrainingSessions from "./TrainingSessions";
import Goals from "./Goals";
import Lessons from "./Lessons";


import {
    Collection, View
} from '@aws-amplify/ui-react';


export const Home = () => {
    const items = [
        <Lessons/>,
        <TrainingSessions/>,
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
                    column={index+1}
                >
                {item}
                </View>
                );
            }}
        </Collection>
    );
};

export default Home;