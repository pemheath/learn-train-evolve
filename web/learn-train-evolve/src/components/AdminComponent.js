import React from 'react';
import {Button, View} from "@aws-amplify/ui-react";
import {Auth} from "aws-amplify";
import axios from "axios";



const AdminComponent = () => {

    const[calId, setCalId] = React.useState(null);

    async function getCurrentUserCalId() {
        try{
        const cognitoUser = await Auth.currentAuthenticatedUser();
        const calId = cognitoUser.attributes['custom:calId'];
        if(calId === null) {throw new Error("No calId found with this user")}
        setCalId(calId);}
        catch(e) {
            console.log("Error retrieving the logged in user's calId", e);
            throw new Error(e);
        }
    }



    async function handleClick() {
        try {
            await getCurrentUserCalId();
            const api = axios.create({
                baseURL: 'http://localhost:3000'
            })
            const queryParams = new URLSearchParams({cal: calId})
            const queryString = queryParams.toString();
            const response = await api.post(`training-sessions?${queryString}`);
            return response.data;
        } catch (error) {
            console.log('Error syncing sessions with user calendar ID:', error);
        }

    }

    return(

        <div>
            <View>
                <Button
                    onClick={handleClick}
                >Sync Training Sessions</Button>
            </View>

        </div>
        );
}

export default AdminComponent;