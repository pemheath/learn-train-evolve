import React from 'react';
import {Button, Card, Text, Collection, Heading, useTheme} from "@aws-amplify/ui-react";



const UpdatedUserTrainingSession = ({userTrainingSession}) => {

    console.log(userTrainingSession.timeAndDate);

    const{tokens} = useTheme();

    const translatePerformance = (performanceRating) => {
        switch (performanceRating) {
            case 1:
                return " Very poor";
            case 2:
                return " Moderately bad";
            case 3:
                return " Average";
            case 4:
                return " Good";
            case 5:
                return " Great";
            default:
                return null;
        }
    }
    const translateTechniqueEnjoyment = (techniqueEnjoyment) => {
        switch (techniqueEnjoyment) {
            case 1:
                return "Hated it.";
            case 2:
                return "Not for me";
            case 3:
                return "I see the utility";
            case 4:
                return "Neutral";
            case 5:
                return "I liked it.";
            case 6:
                return "I loved it.";
            case 7:
                return "One of my favorites.";

            default:
                return null;
        }
    }

    return (
        <div>
                <Card variation = "elevated"
                      backgroundColor = {tokens.colors.neutral[40]}
                >
                    <Text
                    fontWeight={tokens.fontWeights.semibold}
                    >My performance was:
                        <Text
                        fontWeight={tokens.fontWeights.normal}
                        >{translatePerformance(userTrainingSession.performanceRating)}</Text>
                        </Text><br/>
                    <Text fontWeight={tokens.fontWeights.semibold}>Intensity: <Text fontWeight={tokens.fontWeights.normal}> {userTrainingSession.intensityRating} %</Text></Text><br/>
                    <Text fontWeight={tokens.fontWeights.semibold}>Technique:
                        <Text fontWeight={tokens.fontWeights.normal}>{translateTechniqueEnjoyment(userTrainingSession.techniqueEnjoyment)} </Text>
                    </Text><br/>
                    {userTrainingSession.note && <Text fontWeight={tokens.fontWeights.semibold}>Notes:
                        <Text fontWeight={tokens.fontWeights.normal}>{userTrainingSession.note}</Text></Text>} <br/>
                    {(userTrainingSession.goal!=="none") && <Text fontWeight={tokens.fontWeights.semibold}>Goal:
                        <Text fontWeight={tokens.fontWeights.normal}>
                        {userTrainingSession.goal}</Text>
                        </Text>}<br/>
                    <Heading>Tags related to today's session:</Heading>
                    <Collection
                        type = "list"
                        backgroundColor={tokens.colors.white}
                        items={userTrainingSession.tags}
                        gap = "1.rem"
                    >
                        {(item, index) => (
                            <Button
                                key={index}
                                variation = "link"
                                padding={tokens.space.small}
                                tag={item}
                                onClick={()=>alert("When clicked, will pull most recent 10 training sessions with the common tag!")}
                            >{item}</Button>

                        )}
                    </Collection>
                </Card>
        </div>
    );

}

UpdatedUserTrainingSession.displayName="UpdatedUserTrainingSession";

export default UpdatedUserTrainingSession;