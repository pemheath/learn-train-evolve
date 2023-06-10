import React from "react";
import {SelectField} from "@aws-amplify/ui-react";

const GoalSelector = ({ onChange, goals }) => {


    return (
        <SelectField
            label="My goals"
            options={goals}
            onChange={onChange}
            placeHolder="None"
            size="large"
            descriptiveText="Do you want to connect your training to a specific goal you are working toward?"
        ></SelectField>

    );
}

export default GoalSelector;