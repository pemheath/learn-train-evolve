import React, {useEffect} from "react";
import '../styles.css';


import {Link, useLocation} from "react-router-dom";
import ListOfUserTrainingSessions from "./ListOfUserTrainingSessions";
import {Authenticator, Button, Card, Heading, ScrollView, useTheme} from "@aws-amplify/ui-react";


import axios from "axios";
import Header from "./Header";
import PieChartByType from "./PieChartByType";
import ProgressChart from "./ProgressChart";
import TrainingFrequencyByWeek from "./TrainingFrequencyByWeek";
import ltelogo from "../project images/ltelogo.png";


/**
 * The component containing the main functionality around viewing and managing training information
 * This component is accessed in the path /train/:email
 */
function TrainComponent() {


    // allow component to read data passed from the state of the previous component
    const location = useLocation();
    const{tokens} = useTheme();
    const [data, setData] = React.useState([]);
    const [userTrainingSessionList, setUserTrainingSessionList] = React.useState([]);
    const [totalSessions, setTotalSessions] = React.useState(0);

    console.log("email in train component is: ", location.state.email);


    useEffect(() =>{
        const fetchSchedule = async () => {
            let email = location.state.email;

            try {
                const api = axios.create({
                    baseURL: process.env.REACT_APP_API_BASE_URL
                })
                const response = await api.get(`/user-training-sessions/${email}`);
                console.log("user training session for schedule is", response.data.userTrainingSessionModelList);
                setUserTrainingSessionList(response.data.userTrainingSessionModelList);

            } catch (error) {
                console.log("error fetching list of upcoming training sessions", error);
            }
        };

        fetchSchedule();

    }, []);

    useEffect(() =>{
        const getData = async () => {
            let email = location.state.email;
            console.log("Attempting to call endpoint for data vis", email);
            try {
                const api = axios.create({
                    baseURL: process.env.REACT_APP_API_BASE_URL
                })

                const response = await api.get(`/user-training-sessions/${email}?dataVis=true`);
                setData(response.data.userTrainingSessionModelList);
                setTotalSessions(response.data.userTrainingSessionModelList.length);
                console.log("response for getting data for charts is ", response);
            } catch (error) {
                console.log("error fetching data for graphs", error);
            }
        }
        getData();

    }, [userTrainingSessionList]);


    const linkStyle = {
        margin: "1rem",
        textDecoration: "none",
        border:  "1px solid hsl(190, 50%, 50%)",
        display: "inline-block",
        padding: "0.5rem",
        backgroundColor: "white",
        fontWeight: "bold",
        color: "hsl(190, 50%, 50%)",
        borderRadius: "0.5rem",
        hoverBackgroundColor: "hsl(190, 75%, 95%)",
        hoverColor: "white",
    };

    const signinStyle = {
        display: 'flex',
        justifyContent: 'flex-end',
    }


    return (
            <Authenticator>
                {({ signOut, user }) => (
                    <main>
                        <Header/>
                        <div style={signinStyle}>
                        <Button
                            variation="menu"
                            border={tokens.borderWidths.small}
                            borderRadius={tokens.radii.large}
                            onClick={signOut}
                        >Sign Out</Button></div>
                        <Link to={".."} style={linkStyle}>Return Home</Link>
                        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gridTemplateRows: '1fr 1fr 1fr', height: '100vh' }}>
                            <div style={{ gridColumn: '1', gridRow: '1 / span 3' }}>
                                <ScrollView
                                    height="900px"
                                    width="600px"
                                    padding="1rem"
                                    backgroundColor={tokens.colors.brand.primary[60]}>
                                    <ListOfUserTrainingSessions
                                        userTrainingSessionList={userTrainingSessionList}
                                    />
                                </ScrollView>
                            </div>
                        <div style={{ gridColumn: '2 ', gridRow: '1' }}>
                            <ProgressChart
                                totalSessions={totalSessions}></ProgressChart>
                        </div>
                        <div style={{ gridColumn: '2', gridRow: '2' }}>
                                <PieChartByType
                                    data={data}/>
                        </div>
                        <div style={{ gridColumn: '2', gridRow: '3' }} >
                            <TrainingFrequencyByWeek
                                data={data} />
                        </div>
                </div>
            </main>
                )}
            </Authenticator>
        );

    }
TrainComponent.displayName="TrainComponent";

export default TrainComponent;