import React from "react";
import '../styles.css';
import {Button, Card, Flex, Heading, Text, useTheme} from "@aws-amplify/ui-react";
import YouTube from 'react-youtube';


const Lessons = () => {

    const {tokens} = useTheme();

    const handleClick = () => {
        alert("More resources coming soon");
    }

    const youTubeId1 = 'SRG8FOalso0';
    const youTubeId2 = 'Jtxus6VFnnY';

    const labelStyle = {
        transform: "rotate(90deg)"
    }
    return(
        <Flex direction = "row" align = "center">
            <Heading level={1} textAlign={"center"} fontFamily={tokens.fonts.default.variable} color={tokens.colors.brand.primary[100]} fontWeight={tokens.fontWeights.semibold} transform={"rotate(270deg) translateY(50%)"}>Learn</Heading>
                <Card
                padding={tokens.space.large}
                backgroundColor={tokens.colors.background.primary}
                ><h2 style={{textAlign:'center'}}>Dalpra Backtake Study</h2>
                    <YouTube
                    videoId={youTubeId1}
                    />


                </Card>
                <Card
                    padding={tokens.space.large}
                    backgroundColor={tokens.colors.background.primary}
                >
                    <h2 style={{textAlign:'center'}}>Monteiro Finishes</h2>
                    <YouTube
                        videoId={youTubeId2}
                    />
                </Card>
        </Flex>

    );
}

Lessons.displayName="Lessons";
export default Lessons;