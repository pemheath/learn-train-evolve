import React from "react";
import '../styles.css';
import {Card, Heading, Flex, Button, Text, useTheme, View, Collection, ScrollView} from '@aws-amplify/ui-react';


const Goals = () => {
    const {tokens} = useTheme();

    const backgroundStyle = {
        backgroundColor: tokens.colors.brand.primary[80],
        padding: tokens.space.small,
        borderRadius: tokens.radii.medium,

    }

    return (
    <div >

            <Flex direction="column" >
                <Heading level={2} textAlign={"center"} fontFamily={tokens.fonts.default.variable}  color={tokens.colors.brand.primary[100]} fontWeight={tokens.fontWeights.semibold}>Evolve</Heading>
                <Heading level={5} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>My Goals</Heading>
                <Card style={backgroundStyle}>
                <ScrollView style={backgroundStyle}>
                <Card
                variation={"elevated"}
                borderRadius={tokens.radii.medium}
                margin={tokens.space.small}
                >
                    <Text>Train Four Sessions per Week</Text>
                    <Button>Log Progress</Button>
                </Card>
                <Card
                    variation={"elevated"}
                    borderRadius={tokens.radii.medium}
                    margin={tokens.space.small}
                >
                    <Text>Defend the berimbolo</Text>
                    <Button>Log Progress</Button>
                </Card>
                </ScrollView>
            </Card>
            </Flex>

    </div>
    );
}
Goals.displayName="Goals";
export default Goals;