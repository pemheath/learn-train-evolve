import React, {useState} from 'react';
import {SliderField, ToggleButton, ToggleButtonGroup, Button, Card} from "@aws-amplify/ui-react";
import axios from "axios";
import {Auth} from "aws-amplify";


const LogTrainingSessionForm= ()=> {

    const[showForm, setShowForm] = useState(false);
    const[status, setStatus] = setStatus('inProgress');
    const[intensityRating, setIntensityRating] = useState(0);
    const[techniqueEnjoyment, setTechniqueEnjoyment] = useState(0);
    const[performanceRating, setPerformanceRating] = useState(0);
    const[goalNumber, setGoalNumber] = useState(0);
    const[tags, setTags] = useState([]);
    const[updatedSession, setUpdatedSession] = useState([]);

    const getUserInfo = async ()=> {
        const congnitoUser = await Auth.currentAuthenticatedUser();
        const { email, name } = congnitoUser.signInUserSession.idToken.payload;
        return { email, name };
    }

    function handleCheckIn(e){
        e.preventDefault();
        setShowForm(true);
    }

    const handleChange = (e) => {
        setData({ ...data, [e.target.name]: e.target.value });
    };

    async function handleSubmit(e) {
        e.preventDefault();
        setShowForm(false);
        setStatus('submitted');
        try {
            const api = axios.create({
                baseURL: 'http://localhost:3000'
            })
            const result = await api.put('/user-training-sessions/{email}/{eventId}',
                {
                    email: (await getUserInfo()).email,
                    eventId: eventId,
                    timeAndDate: timeAndDate,
                    type: type,
                    coach: coach,
                    intensityRating: intensityRating,
                    techniqueEnjoyment: techniqueEnjoyment,
                    performanceRating: performanceRating,
                    noteNumber: noteNumber,
                    goalNumber: goalNumber,
                    tags: tags,
                    attended: true,

                });
            const userTrainingSession = result.trainingSessionModelList;
            setUpdatedSession(userTrainingSession);
        } catch (error) {
            setError(error);
            console.log("error updating user training session", error);
        }
    }

    return (
        <form>
            (!showForm && <Button //on when form has not been filled out
                variation="primary"
                onClick={handleCheckIn}
                >Record My Training</Button> )
            <SliderField
                label="How did you enjoy the technique?"
                descriptiveText="Rate your enjoyment from 1 (Strongly dislike) to 7 (Strongly like)"
                min={1}
                max={7}
                step={1}
                value={enjoyment}
                onChange={setEnjoyment}
            />
            <SliderField
                label="How did you perform today?"
                descriptiveText="Rate your perfromance, form 1 (I shit the bed) to 7 (At my best)"
                type="performanceRating"
                min={1}
                max={7}
                step={1}
                value={performance}
                onChange={setPerformance}
            />
            <SliderField
                label="How intense was your training?"
                type="techniqueEnjoyment"
                min={1}
                max={100}
                value={intensity}
                onChange={setIntensity}
            />
            <ToggleButtonGroup
                value={feeling}
                onChange={setFeeling}
            >
                <ToggleButton value="1">

                </ToggleButton>
                <ToggleButton value="2">

                </ToggleButton>
                <ToggleButton value="3">

                </ToggleButton>
                <ToggleButton value="4">

                </ToggleButton>
                <ToggleButton value="5">

                </ToggleButton>
            </ToggleButtonGroup>
            <Button onClick={handleSubmit}>Submit</Button>

         <Card {status=='submitted'}>
            <p>updatedSession</p>
        </Card>

        </form>
    );
}

export default LogTrainingSessionForm;