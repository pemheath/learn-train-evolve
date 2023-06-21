import React, {useEffect} from 'react';
import {Alert, Button, Card, View} from "@aws-amplify/ui-react";
import axios from "axios";




const AdminComponent = ({cognitoUser}) => {
    const[message, setMessage]= React.useState('');
    const[loading, setLoading] = React.useState(false);
    const [admin, setAdmin] = React.useState(false);
    const[alert, setAlert] = React.useState(false);
    const [fail, setFail] = React.useState(false);

    /**
     * Call fetch user groups one time on page load.
     */

    useEffect(() => {
        async function fetchUserGroups() {
            try {
                console.log("calling fetch usergroups");
                const groups = cognitoUser.signInUserSession.accessToken.payload['cognito:groups'];
                if((groups!==null) && groups.includes('admin')) {
                    console.log("admin");
                    setAdmin(true);
                }
                if (groups ===null) {
                    console.log("not admin");
                    setAdmin(false);
                }
            } catch (error) {
                console.log('Error fetching user groups:', error);
            }
        }

        fetchUserGroups();

    }, []);


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
                setAlert(true);


            } catch (error) {
                setLoading(false);
                setFail(true);
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
                    {alert &&<Alert
                    isDismissible={true}
                    variation={"success"}
                    >
                        Training sessions successfully synced!
                    </Alert>}
                    {fail &&<Alert
                        isDismissible={true}
                        onDismiss={() => setFail(false)}
                        variation={"error"}
                    >
                        Training sessions successfully synced!
                    </Alert>}
                </Card>
            </View>
            }

        </div>
        );
}
AdminComponent.displayName="AdminComponent";

export default AdminComponent;