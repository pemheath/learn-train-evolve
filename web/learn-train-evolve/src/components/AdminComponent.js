import React, {useEffect} from 'react';
import {Button, Card, View, Text} from "@aws-amplify/ui-react";
import axios from "axios";
import App from "../App";
import {Auth} from "aws-amplify";



const AdminComponent = ({cognitoUser}) => {
    const[message, setMessage]= React.useState('');
    const[loading, setLoading] = React.useState(false);
    const[groups, setGroups] = React.useState([]);

    const [admin, setAdmin] = React.useState(false);

    useEffect(() => {
        async function fetchUserGroups() {
            try {
                console.log("calling fetch usergroups");
                const groups = cognitoUser.signInUserSession.accessToken.payload['cognito:groups'];
                console.log("groups", groups);
                setGroups(groups);
                if (groups.includes('admin')) {
                    console.log("user is admin");
                    setAdmin(true);
                }

            } catch (error) {
                console.log('Error fetching user groups:', error);
            }
        }

        fetchUserGroups(); // Call the function to execute it

    }, []); // Empty dependency array for running the effect once


    async function handleClick() {
        setLoading(true);
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
                setLoading(false);
                alert(message);

            } catch (error) {
                setLoading(false);
                console.log('Error syncing sessions with user calendar ID:', error);
            }
        }

    }
    console.log(message);

    return(

        <div>
            {admin &&
            <View>
                <Card>
                    <Button
                        onClick={handleClick}
                        isLoading={loading}
                    >Sync Training Sessions</Button>
                </Card>
            </View>
            }

        </div>
        );
}
AdminComponent.displayName="AdminComponent";

export default AdminComponent;