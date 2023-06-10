import React, {useState} from 'react';
import {
    SliderField,
    TextAreaField,
    ToggleButton,
    ToggleButtonGroup,
    Button,
    Card,
    useTheme,
    View,
    SelectField
} from "@aws-amplify/ui-react";
import axios from "axios";
import { ImFrustrated, ImSad, ImNeutral, ImSmile,  ImHappy  } from "react-icons/im";
import TagSelector from "./TagSelector";
import GoalSelector from "./GoalSelector";


const LogTrainingSessionForm= ({email, eventId, timeAndDate, type, coach})=> {

    const {tokens} = useTheme();
    const[error, setError] = useState(null);
    const[showForm, setShowForm] = useState(false);
    const[status, setStatus] = useState('inProgress');
    const[intensityRating, setIntensityRating] = useState(0);
    const[techniqueEnjoyment, setTechniqueEnjoyment] = useState(0);
    const[performanceRating, setPerformanceRating] = useState("3");
    const[note, setNote] = useState("");
    const[tags, setTags] = useState([]);

    function handleCheckIn(e){
        e.preventDefault();
        setShowForm(true);
    }

    async function handleSubmit(e) {
        console.log("here is the data for the request object");
        console.log("intensity rating is: " + intensityRating);
        console.log("technique enjoyment is: " + techniqueEnjoyment);
        console.log("performance rating is: " + performanceRating);
        console.log("note is: " + note);
        console.log("tags are: " + tags);
        console.log("eventId is: " + eventId);
        console.log("email is: " + email);
        console.log("timeAndDate is: " + timeAndDate);
        console.log("type is: " + type);
        console.log("coach is: " + coach);
        e.preventDefault();
        setShowForm(false);
        setStatus('submitted');
        try {
            const api = axios.create({
                baseURL: 'http://localhost:3000'
            })
            const result = await api.put('/user-training-sessions/{email}/{eventId}',
                {
                    email: encodeURIComponent(email), //from props
                    eventId: eventId, //from props
                    timeAndDate: timeAndDate, //from props
                    type: type, //from props
                    coach: coach, //from props
                    intensityRating: intensityRating, //user input
                    techniqueEnjoyment: techniqueEnjoyment, //user input
                    performanceRating: performanceRating, //user input
                    note: note, //not adding this functionality yet
                    goal: note, //not adding this functionality yet
                    tags: tags, //user input
                    attended: true,

                });
            const userTrainingSession = result.data.userTrainingSession;
        } catch (error) {
            setError(error);
            console.log("error updating user training session", error);
        }
    }

    return (
        <form>
            {!showForm &&
            <Button //on when form has not been filled out
                variation="primary"
                onClick={handleCheckIn}
            >Record My Training</Button>}
            {showForm&&
            <View
                position="relative"
            >
            <Card
                variation="elevated"
                display="inline-block"
            >
            <ToggleButtonGroup
                isExclusive
                value={performanceRating}
                onChange={(value) => setPerformanceRating(value[0])}
                variation="menu"
            >How was your performance today?
                <ToggleButton value="1">
                    <ImFrustrated/>
                </ToggleButton>
                <ToggleButton value="2" >
                    <ImSad/>
                </ToggleButton>
                <ToggleButton value="3">
                    <ImNeutral/>
                </ToggleButton>
                <ToggleButton value="4">
                    <ImSmile/>
                </ToggleButton>
                <ToggleButton value="5">
                    <ImHappy/>
                </ToggleButton>
            </ToggleButtonGroup>

            <SliderField
                label="Rate your enjoyment of today's technique."
                descriptiveText="Hate it, love it?"
                filledTrackColor={tokens.colors.brand.secondary[80]}
                min={1}
                max={7}
                step={1}
                value={techniqueEnjoyment}
                onChange={setTechniqueEnjoyment}
            />
            <SliderField
                label="How intense was your training?"
                descriptiveText="Chill day of drilling, or all out competition class?"
                filledTrackColor={tokens.colors.brand.secondary[80]}
                min={1}
                max={100}
                value={intensityRating}
                onChange={setIntensityRating}
            />
            <Card>
                <TagSelector
                    tags={["come up sweep", "guard retention", "single leg takedown", "pressure passing", "submission defense", "back control", "conditioning", "mindset"]}
                />
            </Card>

            <TextAreaField
                label="Notes from today"
            ></TextAreaField >
                <GoalSelector goals={["Not today", "Train 4 days in one week", "Manage frustration while rolling with Taylor", "Defend the berimbolo"]}
                              ></GoalSelector>
            <Button onClick={handleSubmit} size="large"> Submit</Button>
            </Card>
            </View>}
        </form>

    );
}

export default LogTrainingSessionForm;