import React from "react";
import '../styles.css';
import TrainingSessions from "./TrainingSessions";
import Goals from "./Goals";
import Lessons from "./Lessons";


import {
    Collection,
    Card,
    Heading,
    Text,
    useTheme,
} from '@aws-amplify/ui-react';

export const Home = () => {
    const { tokens } = useTheme();
    const items = [
        <Lessons/>,
        <TrainingSessions/>,
        <Goals/>
    ];

    return (
        <Collection
            type="list"
            items={items}
            direction="row"
            justifyContent="space-between"
            wrap="no-wrap"
        >
            {(item, index) => (
                <div>
                    {item}
                </div>
            )}
        </Collection>
    );
};

export default Home;