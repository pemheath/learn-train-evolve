import React from "react";
import '../styles.css';
import {Button, Card, Flex, Heading, Text, ThemeProvider} from "@aws-amplify/ui-react";
import theme from "./Theme"

const Lessons = () => {

    return(
    <ThemeProvider theme={theme}>
        <Card variation = "elevated">
                <Heading level={5}>
                    Learn
                </Heading>
                <Text as="span">The expert in anything was once a beginner.</Text>
                <Button variation = "primary" >Study</Button>

        </Card>
    </ThemeProvider>
    );
}

export default Lessons;