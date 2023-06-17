import React from "react";
import '../styles.css';
import {Card, Heading, Flex, Button, Text, ThemeProvider, useTheme} from '@aws-amplify/ui-react';
import theme from './Theme'
import App from "../App";


const Goals = () => {
    const {tokens} = useTheme();
    return (
    <div>
        <ThemeProvider theme ={theme}>
        <Card
            variation={"elevated"}
        >
            <Flex direction = "column" alignItems="flex-start">
                <Heading level={5}>
                    Evolve
                </Heading>
                <Text as="span">If you don't know where you are going, you will probably end up somewhere else.</Text>
                <Button variation = "primary" >Track progress</Button>
            </Flex>
        </Card>
        </ThemeProvider>

    </div>
    );
}
Goals.displayName="Goals";
export default Goals;