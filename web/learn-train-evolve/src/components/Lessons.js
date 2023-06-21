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

    return(
        <Flex direction = "column" align = "center">
            <Heading level={3} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Learn</Heading>
                <Button
                    margin={tokens.space.medium}
                    variation="primary" onClick={handleClick}>See My Resources</Button>
                <Card
                padding={tokens.space.large}
                backgroundColor={tokens.colors.background.primary}
                ><h2>Dalpra Backtakes</h2>
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
                       Study this for backtake options.
                    </Text>
                    <YouTube
                    videoId={youTubeId1}
                    opts={{
                        playerVars: {
                            autoplay: 1,
                        }}}
                    />


                </Card>
                <Card
                    padding={tokens.space.large}
                    backgroundColor={tokens.colors.background.primary}
                >
                    <h2>Monteiro Finishes</h2>
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
                        Four amazing finishes to study
                    </Text>
                    <YouTube
                        videoId={youTubeId2}
                        opts={{
                            playerVars: {
                                autoplay: 1,
                            }}}
                    />
                </Card>
        </Flex>

    );
}

Lessons.displayName="Lessons";
export default Lessons;