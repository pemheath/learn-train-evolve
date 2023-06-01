import React from "react";
import axios from "axios";
import {useEffect} from "react";
import SingleTrainingSession from "./SingleTrainingSession";

import {
    Collection,
    ScrollView,
    Heading,
    Text,
    useTheme,
} from '@aws-amplify/ui-react';

 const TrainingSessions = () => {

     const [trainingSessionList, setTrainingSessionList] = React.useState([]);

     useEffect(() => {fetchSessions();}, []);

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
             <h2 style={{fillOpacity: "0.5"}}>Training Sessions</h2>
             <ScrollView height = "300px" width = "400px" padding = "1rem">
             <Collection
                 type = "list"
                 items={trainingSessionList}
                 gap = "1.rem"
             >
                 {(item, index) => (
            <SingleTrainingSession
                key={index}
                trainingSession={item}/>
                 )}
             </Collection>
             </ScrollView>
             </div>
         );
 };

 export default TrainingSessions;