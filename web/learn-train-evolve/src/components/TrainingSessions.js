import React from "react";
import axios from "axios";
import SingleTrainingSession from "./SingleTrainingSession";

import {
    Collection,
    ScrollView,
    Flex,
    Heading,
    useTheme, Button,
} from '@aws-amplify/ui-react';

import {useNavigate} from 'react-router-dom';


 const TrainingSessions = ({email}) => {


     const [trainingSessionList, setTrainingSessionList] = React.useState([]);

     const [display, setDisplay] = React.useState(false);

     const navigate = useNavigate();

     React.useEffect(() => {
         async function fetchSessions (){
             console.log("fetchSessions called");
             try {
                 const api = axios.create({
                     baseURL: process.env.REACT_APP_API_BASE_URL
                 })
                 const response = await api.get(`/training-sessions`);
                 const trainingSessionList = response.data.trainingSessionModelList;
                 setTrainingSessionList(trainingSessionList);
                 console.log("response is", response);
                 console.log("training session model list is: +", trainingSessionList);

             } catch (error) {
                 console.log("error fetching training sessions", error);
             }
         };
         fetchSessions();
     }, []);

     const handleClick = () => {
         setDisplay(!display);
     }


     const { tokens } = useTheme();



         return (
             <div>
                     <Flex
                         direction="column"
                     >
                     <Heading level={2} textAlign={"center"} fontFamily={tokens.fonts.default.variable}  color={tokens.colors.brand.primary[100]} fontWeight={tokens.fontWeights.semibold}>Train</Heading>
                         <Button onClick={handleClick}>{display ? "Hide Schedule" : "Show Schedule"}</Button>
                         {display&& <Heading  level={5} textAlign={"center"} fontFamily={tokens.fonts.default.variable}>Upcoming Training Sessions</Heading>}
                         {display&&
                             <ScrollView
                         padding = "1rem"
                         borderRadius={tokens.radii.medium}
                         backgroundColor={tokens.colors.brand.primary[80]}>
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
                     </ScrollView>}
                     </Flex>
             </div>
         );
 };

TrainingSessions.displayName="TrainingSessions";

 export default TrainingSessions;