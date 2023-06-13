import React from "react";
import axios from "axios";
import {useEffect} from "react";
import SingleTrainingSession from "./SingleTrainingSession";

import {
    Collection,
    ScrollView,
    Flex,
    Heading,
    useTheme
} from '@aws-amplify/ui-react';
import {Auth} from "aws-amplify";
import userTrainingSession from "./UserTrainingSession";
import updatedUserTrainingSession from "./UpdatedUserTrainingSession";
import UserTrainingSession from "./UserTrainingSession";


const ListOfUserTrainingSessions = () => {

    const [userTrainingSessionList, setUserTrainingSessionList] = React.useState([]);
    useEffect(() =>{ fetchSessions();},[]);
    const {tokens} = useTheme();


    const getUserInfo = async ()=> {
        const cognitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = cognitoUser.signInUserSession.idToken.payload;
        return {email, name};
    }

    const fetchSessions = async () => {
        const authenticatedEmail = (await getUserInfo()).email;

        console.log("email for user training sessions is" + authenticatedEmail);
        try {
            const api = axios.create({
                baseURL: 'http://localhost:3000'
            })
            const response = await api.get(`/user-training-sessions/${authenticatedEmail}`);

            setUserTrainingSessionList(response.data.userTrainingSessionModelList);
        } catch (error) {
            console.log("error fetching training sessions", error);
        }
    }

    return (
        <div>
            <Flex
                direction="column"
            >
                <Heading level={2} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Train</Heading>
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

                </ScrollView>
            </Flex>
        </div>);

}

export default ListOfUserTrainingSessions;