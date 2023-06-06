import React from "react";
import '../styles.css';
import {Card, Heading, Flex, Button, Text} from '@aws-amplify/ui-react';

const css = `.custom-card-class {
    border: 3px solid gray;
    background-color: #cccccc;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}`

const Goals = () => {
    return (
    <div>
        <style>{css}</style>
        <Card variation = "outlined" className="custom-card-class">
            <Flex direction = "column" alignItems="flex-start">
                <Heading level={5}>
                    Evolve
                </Heading>
                <Text as="span">If you don't know where you are going, you will probably end up somewhere else.</Text>
                <Button variation = "primary" className="btn-1">Track progress</Button>
            </Flex>
        </Card>

    </div>
    );
}

export default Goals;