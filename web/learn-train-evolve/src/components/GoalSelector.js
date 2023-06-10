import React, {useState} from "react";
import {SelectField, useTheme} from "@aws-amplify/ui-react";

const GoalSelector = ({ goals }) => {

    const[goal, setGoal] = useState("none");
    const{tokens} = useTheme();

    return (
        <SelectField
            label="My goals"
            value={goal}
            onChange={(e)=>setGoal(e.target.value)}
            options={goals}
            placeHolder="None"
            size="large"
            descriptiveText="Do you want to connect your training to a specific goal you are working toward?"
        ></SelectField>

    );
}

export default GoalSelector;