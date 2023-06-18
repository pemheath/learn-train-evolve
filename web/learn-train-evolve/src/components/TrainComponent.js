import React, {useEffect} from "react";
import '../styles.css';


import {useLocation} from "react-router-dom";
import UserTrainingSession from "./UserTrainingSession";
import ListOfUserTrainingSessions from "./ListOfUserTrainingSessions";
import {Button, ScrollView, SelectField, useTheme} from "@aws-amplify/ui-react";


import axios from "axios";
import Header from "./Header";
import PieChartByType from "./PieChartByType";
import ProgressChart from "./ProgressChart";
import IntensityAndPerformanceGraph from "./IntensityAndPerformanceGraph";


function TrainComponent() {

    // allow component to read data passed from the state of the previous component
    const location = useLocation();
    const{tokens} = useTheme();
    console.log("in the train component");
    const [data, setData] = React.useState([]);
    const [totalSessions, setTotalSessions] = React.useState(0);
    const [dataChartType, setDataChartType] = React.useState("IntensityAndPerformance");

    useEffect(() =>{
            const getData = async () => {
                let email = location.state.userTrainingSession.email;

                console.log("email for fetching all user training sessions is" + email);
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

    const handleClick = () => {
        setDataChartType("IntensityAndPerformance");
        console.log(dataChartType);
    }


        return (
            <div>
            <Header/>

            <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gridTemplateRows: '1fr 1fr', height: '100vh' }}>
                <div style={{ gridColumn: '1', gridRow: '1 / span 2' }}>
                    <ScrollView
                        height="900px"
                        width="600px"
                        padding="1rem"
                        backgroundColor={tokens.colors.brand.primary[60]}>
                        <UserTrainingSession
                            userTrainingSession={location.state.userTrainingSession}
                        />
                        <div>
                            <ListOfUserTrainingSessions
                                email={location.state.userTrainingSession.email}
                            />
                        </div>
                    </ScrollView>
                </div>
                <div style={{ gridColumn: '2 ', gridRow: '1' }}>
                    <ProgressChart
                        totalSessions={totalSessions}></ProgressChart>
                </div>
                <div style={{ gridColumn: '2', gridRow: '2' }}>
                    <SelectField
                        label="Analyze My Training"
                        options={["View By Class Type", "Training Frequency", "Performance And Intensity"]}
                        placeHolder="None"
                        size="large"
                        descriptiveText="Choose your display"
                        onClick={event => setDataChartType(event.target.value)}
                    ></SelectField>
                    {dataChartType==="View By Class Type" &&
                        <PieChartByType
                            data={data}
                        />}
                    {dataChartType==="IntensityAndPerformance" &&
                        <IntensityAndPerformanceGraph
                            data={data}
                        />}
                </div>
            </div>
            </div>

        );

    }
TrainComponent.displayName="TrainComponent";

export default TrainComponent;