import React from "react";
import '../styles.css';
import {Card, Heading, Flex, Button, Text, ThemeProvider, useTheme} from '@aws-amplify/ui-react';
import theme from './Theme'
import App from "../App";


const Goals = () => {
    const {tokens} = useTheme();
    return (
    <div>
        <Flex direction="column">
            <Heading level={3} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Evolve</Heading>
            <Button>Manage My Goals</Button>
            <Heading level={5} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>My Goals</Heading>
            <Card
            variation={"elevated"}
            >
                <Text>Train Four Sessions per Week</Text>
                <Button>Log Progress</Button>
            </Card>
            <Card
                variation={"elevated"}
            >
                <Text>Defend the berimbolo</Text>
                <Button>Log Progress</Button>
            </Card>

        </Flex>

    </div>
    );
}
Goals.displayName="Goals";
export default Goals;