import React from "react";
import axios from "axios";


import {
    Collection,
    ScrollView,
    Flex,
    Heading,
    useTheme, Button
} from '@aws-amplify/ui-react';
import {Auth} from "aws-amplify";

import UserTrainingSession from "./UserTrainingSession";


const ListOfUserTrainingSessions = () => {

    const [userTrainingSessionList, setUserTrainingSessionList] = React.useState([]);
    const {tokens} = useTheme();


    const getUserInfo = async ()=> {
        const cognitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = cognitoUser.signInUserSession.idToken.payload;
        return {email, name};
    }

    const fetchSessions = async () => {
        const authenticatedEmail = (await getUserInfo()).email;

        console.log("email for fetching all user training sessions is" + authenticatedEmail);
        try {
            const api = axios.create({
                baseURL: `${process.env.REACT_APP_API_BASE_URL}`
            })
            const response = await api.get(`/user-training-sessions/${authenticatedEmail}`);
            console.log(response.status, response.data);

            setUserTrainingSessionList(response.data.userTrainingSessionModelList);
        } catch (error) {
            console.log("error fetching user training sessions", error);
        }
    }

    return (
        <div>
            <Flex
                direction="column"
            >
                <Heading level={2} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Train</Heading>
                <Button
                    onClick={fetchSessions}
                    variation="link"
                >
                    View My Sessions For The Week
                </Button>
                ({userTrainingSessionList} &&
                <ScrollView
                    height = "300px"
                    width = "400px"
                    padding = "1rem"
                    backgroundColor={tokens.colors.brand.primary[60]}>
                    <Collection
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
                    </Collection>

                </ScrollView>)
            </Flex>
        </div>);

}

export default ListOfUserTrainingSessions;