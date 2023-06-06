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
    Text
} from '@aws-amplify/ui-react';

const css = `.custom-card-class {
    border: 3px solid gray;
    background-color: #e6f0ff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  
}`

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
                 <style>{css}</style>
                 <Card variation="outlined" className="custom-card-class">
                     <Flex direction="column">
                     <Heading level={5} textAlign={"center"}>Train</Heading>
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
                     </Flex>
                 </Card>
             </div>
         );
 };

 export default TrainingSessions;