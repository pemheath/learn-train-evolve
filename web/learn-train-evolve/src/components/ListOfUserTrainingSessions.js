import React from "react";

import {
    Collection,
    Flex,
    Heading,
    useTheme, Button
} from '@aws-amplify/ui-react';

import UserTrainingSession from "./UserTrainingSession";

import {Outlet} from "react-router-dom";

const ListOfUserTrainingSessions = ({userTrainingSessionList}) => {

    const {tokens} = useTheme();
    const [displayList, setDisplayList] = React.useState(true);

    const handleClick = () => {
        setDisplayList(!displayList);

    }

    return (
        <div>
            <Flex
                direction="column"
            >
                <Heading level={2} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Train</Heading>
                <Button
                    onClick={handleClick}
                    variation="link"
                >
                      {displayList ? "Hide Schedule" : "Show Schedule"}
                </Button>

                {displayList && <Collection
                        type = "list"
                        backgroundColor={tokens.colors.white}
                        items={userTrainingSessionList}
                        gap = "1.rem"
                    >
                        {(item, index) => (
                            <UserTrainingSession
                                key={index}
                                userTrainingSession={item}
                            />
                        )}
                    </Collection>}
            </Flex>
            <Outlet/>
        </div>
    );

}

ListOfUserTrainingSessions.displayName="ListOfUserTrainingSessions";

export default ListOfUserTrainingSessions;