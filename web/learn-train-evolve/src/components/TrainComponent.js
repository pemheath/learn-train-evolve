import React, {useEffect} from "react";
import '../styles.css';


import {useLocation} from "react-router-dom";
import ListOfUserTrainingSessions from "./ListOfUserTrainingSessions";
import {Authenticator, Button, Heading, ScrollView, SelectField, useTheme} from "@aws-amplify/ui-react";


import axios from "axios";
import Header from "./Header";
import PieChartByType from "./PieChartByType";
import ProgressChart from "./ProgressChart";
import TrainingFrequencyByWeek from "./TrainingFrequencyByWeek";


function TrainComponent() {


    // allow component to read data passed from the state of the previous component
    const location = useLocation();
    const{tokens} = useTheme();
    const [data, setData] = React.useState([]);
    const [totalSessions, setTotalSessions] = React.useState(0);

    useEffect(() =>{
            const getData = async () => {
                let email = location.state.email;
                try {
                    const api = axios.create({
                        baseURL: `${process.env.REACT_APP_API_BASE_URL}`
                    })
                    const response = await api.get(`/user-training-sessions/${email}?dataVis=true`);
                    setData(response.data.userTrainingSessionModelList);
                    setTotalSessions(response.data.userTrainingSessionModelList.length);

                } catch (error) {
                    console.log("error fetching data", error);
                }
            }
            getData(); },
        []);

        return (
            <Authenticator>
                {({ signOut, user }) => (
                    <main>
                        <Header/>
                        <Heading level={3} textAlign={"center"}> Welcome {user.attributes.name}</Heading>
                        <Button
                            variation="menu"
                            border={tokens.borderWidths.small}
                            borderRadius={tokens.radii.large}
                            onClick={signOut}
                        >Sign Out</Button>
                        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gridTemplateRows: '1fr 1fr 1fr', height: '100vh' }}>
                            <div style={{ gridColumn: '1', gridRow: '1 / span 3' }}>
                                <ScrollView
                                    height="900px"
                                    width="600px"
                                    padding="1rem"
                                    backgroundColor={tokens.colors.brand.primary[60]}>
                                    <ListOfUserTrainingSessions
                                        email={location.state.email}
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