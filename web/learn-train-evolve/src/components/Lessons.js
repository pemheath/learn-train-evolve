import React from "react";
import '../styles.css';
import {Button, Card, Flex, Heading, Text} from "@aws-amplify/ui-react";

const css = `.custom-card-class {
    border: 3px solid gray;
    background-color: #e6f0ff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}`
const Lessons = () => {
    return(
    <div>
        <style>{css}</style>
        <Card variation = "outlined" className="custom-card-class">
            <Flex direction = "column" alignItems="flex-start">
                <Heading level={5}>
                    Learn
                </Heading>
                <Text as="span">The expert in anything was once a beginner.</Text>
                <Button variation = "primary" className="btn-1">Study</Button>
            </Flex>
        </Card>
    </div>
    );
}

export default Lessons;