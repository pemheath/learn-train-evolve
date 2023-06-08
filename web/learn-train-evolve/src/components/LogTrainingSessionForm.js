import React from 'react';
import {SliderField, ToggleButton, ToggleButtonGroup, Button} from "@aws-amplify/ui-react";
import SentimentDissatisfiedIcon from '@mui/icons-material/SentimentDissatisfied';
import SentimentNeutralIcon from '@mui/icons-material/SentimentNeutral';
import SentimentSatisfiedIcon from '@mui/icons-material/SentimentSatisfied';
import SentimentSatisfiedAltIcon from '@mui/icons-material/SentimentSatisfiedAlt';
import SentimentVerySatisfiedIcon from '@mui/icons-material/SentimentVerySatisfied';

const LogTrainingSessionForm= ()=> {


    const [enjoyment, setEnjoyment] = React.useState(4);
    const [intensity, setIntensity] = React.useState(50);
    const [performance, setPerformance] = React.useState(4);
    const [feeling, setFeeling] = React.useState("3");

    return (
        <form>
            <SliderField
                label="How did you enjoy the technique?"
                descriptiveText="Rate your enjoyment from 1 (Strongly dislike) to 7 (Strongly like)"
                type="techniqueEnjoyment"
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
                    <SentimentDissatisfiedIcon/>
                </ToggleButton>
                <ToggleButton value="2">
                    <SentimentNeutralIcon/>
                </ToggleButton>
                <ToggleButton value="3">
                    <SentimentSatisfiedIcon/>
                </ToggleButton>
                <ToggleButton value="4">
                    <SentimentSatisfiedAltIcon/>
                </ToggleButton>
                <ToggleButton value="5">
                    <SentimentVerySatisfiedIcon/>
                </ToggleButton>
            </ToggleButtonGroup>
            <Button>Submit</Button>
        </form>
    );
}

export default LogTrainingSessionForm;