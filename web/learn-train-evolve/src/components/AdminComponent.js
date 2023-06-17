import React from 'react';
import {Button, Card, View, Text} from "@aws-amplify/ui-react";
import axios from "axios";
import App from "../App";



const AdminComponent = ({cognitoUser}) => {
    const[message, setMessage]= React.useState('');
    const[disabled, setDisabled] = React.useState(false);


    async function handleClick() {
        console.log("in handleClick");
        console.log(cognitoUser);
        const calId = cognitoUser.attributes['custom:calId'];
        const confirmed = window.confirm("Are you sure you want to syn your google calendar" + calId + "to the " +
            "training sessions for LTE?");
        console.log("calID", calId);
        if (confirmed) {
            try {
                const api = axios.create({
                    baseURL: `${process.env.REACT_APP_API_BASE_URL}`
                })
                const url = `training-sessions?cal=${calId}`
                console.log("calling post with url", url);
                const response = await api.post(`training-sessions?cal=${calId}`);
                setMessage(response.data.message);
                console.log(message);
                setDisabled(true);

            } catch (error) {
                console.log('Error syncing sessions with user calendar ID:', error);
            }
        }

    }
    console.log(message);

    return(

        <div>
            <View>
                <Card>
                    <Button
                        onClick={handleClick}
                        isDisabled={disabled}
                    >Sync Training Sessions</Button>
                    <Text>
                        {message}
                    </Text>
                </Card>
            </View>

        </div>
        );
}
AdminComponent.displayName="AdminComponent";

export default AdminComponent;