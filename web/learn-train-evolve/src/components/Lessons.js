import React from "react";
import '../styles.css';
import {Button, Card, Flex, Heading, Text, useTheme} from "@aws-amplify/ui-react";


const Lessons = () => {

    const {tokens} = useTheme();

    const handleClick = () => {
        alert("More resources coming soon");
    }

    return(
        <Flex direction = "column" align = "center">
            <Heading level={3} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Learn</Heading>
                <Button
                    margin={tokens.space.medium}
                    variation="primary" onClick={handleClick}>See My Resources</Button>
                <Card
                padding={tokens.space.large}
                backgroundColor={tokens.colors.background.primary}
                >
                    <Text
                        variation="primary"
                        as="p"
                        textAlign="center"
                        lineHeight="2.5em"
                        fontWeight={400}
                        fontSize="1.5em"
                        fontStyle="normal"
                        textDecoration="none"
                        width="30vw"
                    >
                        De la riva to tripod sweep
                    </Text>
                <iframe
                    width="540"
                    height="300"
                    src={`https://www.youtube.com/embed/$wVddbE3q7fY&list=PLh8zH5HfF7LG9cqV2J79b7Hdg7g1BDhRA&index=6`}
                    title="Tripod Sweep"
                ></iframe>
                </Card>
                <Card
                    padding={tokens.space.large}
                    backgroundColor={tokens.colors.background.primary}
                >
                    <Text
                        variation="primary"
                        as="p"
                        textAlign="center"
                        lineHeight="2.5em"
                        fontWeight={400}
                        fontSize="1.5em"
                        fontStyle="normal"
                        textDecoration="none"
                        width="30vw"
                    >
                        Spider Lasso Attack
                    </Text>
                    <iframe
                        width="540"
                        height="300"
                        src={`https://www.youtube.com/shorts/OTDzW6bvq3A`}
                        title="Tripod Sweep"
                    ></iframe>
                </Card>
        </Flex>

    );
}

Lessons.displayName="Lessons";
export default Lessons;