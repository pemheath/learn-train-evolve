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
    const [email, setEmail] = React.useState('');
    const [name, setName] = React.useState('');
    useEffect(() =>{ fetchSessions(); getUserInfo();},[]);
    const {tokens} = useTheme();

    const getUserInfo = async ()=> {
        const cognitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = cognitoUser.signInUserSession.idToken.payload;
        setEmail(email);
        setName(name);
    }
    useEffect(() => {

        const epochTime = userTrainingSession.timeAndDate;
        const date = new Date(epochTime * 1000); // Convert epoch time to milliseconds (*1000)
        const readableDate = date.toLocaleString(); // Convert to readable format
        console.log("readable date is" + readableDate);

// Define the options for formatting the date and time, which will happen when timeAndDate changes
        const options = {
            weekday: 'long', // Display the full name of the weekday
            month: 'long', // Display the full name of the month
            day: 'numeric', // Display the day of the month
            hour: 'numeric', // Display the hour
            minute: 'numeric', // Display the minute
            hour12: true, // Use 12-hour format (am/pm)
        };
        setFormattedDateTime(new Intl.DateTimeFormat('en-US', options).format(date));

    }, [updatedUserTrainingSession.timeAndDate]);

    const fetchSessions = async (email) => {
        try {
            const api = axios.create({
                baseURL: 'http://localhost:3000'
            })
            const response = await api.get(`/user-training-sessions/${email}`);
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