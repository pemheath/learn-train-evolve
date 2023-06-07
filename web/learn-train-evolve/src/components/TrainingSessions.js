import React from "react";
import axios from "axios";
import {useEffect} from "react";
import SingleTrainingSession from "./SingleTrainingSession";

import {
    Collection,
    ScrollView,
    Card,
    Flex,
    Heading,
    Button,
    Text,
    useTheme
} from '@aws-amplify/ui-react';


 const TrainingSessions = () => {

     const [trainingSessionList, setTrainingSessionList] = React.useState([]);

     useEffect(() => {fetchSessions();}, []);

     const { tokens } = useTheme();

     const fetchSessions = async () => {
         try {
             const api = axios.create({
                 baseURL: 'http://localhost:3000'
             })
             const trainingSessionData = await api.get('/training-sessions');
             const trainingSessionList = trainingSessionData.data.trainingSessionModelList;
             setTrainingSessionList(trainingSessionList);
             console.log(trainingSessionList);
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
                         backgroundColor={tokens.colors.white}>
                     <Collection
                         type = "list"
                         backgroundColor={tokens.colors.white}
                         items={trainingSessionList}
                         gap = "1.rem"
                     >
                         {(item, index) => (
                            <SingleTrainingSession
                                key={index}
                                trainingSession={item}
                            />
                         )}
                     </Collection>

                     </ScrollView>
                     </Flex>
             </div>
         );
 };

 export default TrainingSessions;