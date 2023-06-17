import React from "react";
import '../styles.css';

import { useEffect, useState } from 'react';
import { Auth } from 'aws-amplify';
import AdminComponent from "./AdminComponent";
import App from "../App";

function Header() {
    const [userGroups, setUserGroups] = useState([]);
    const [cognitoUser, setCognitoUser] = useState(null);

    useEffect(() => {
        async function fetchUserGroups() {
            try {
                console.log("calling fetch usergroups");
                const user = await Auth.currentAuthenticatedUser();
                setCognitoUser(user);
                console.log(user);

                const groups = user.signInUserSession.accessToken.payload['cognito:groups'];
                setUserGroups(groups || []);
                console.log(groups);
            } catch (error) {
                console.log('Error fetching user groups:', error);
            }
        }

        fetchUserGroups(); // Call the function to execute it

    }, []); // Empty dependency array for running the effect once


    const isUserInGroup = () => {
        console.log("checking if user is in admin");
        const isAdmin = userGroups.includes('admin');
        console.log(isAdmin);
        return isAdmin;
    };

    return (
        <div>
            <header>
                <h1>Learn Train Evolve</h1>
                <div className="container">
                    <div className="white"></div>
                    <div className="blue"></div>
                    <div className="purple"></div>
                    <div className="brown"></div>
                    <div className="black"></div>
                </div>
            </header>
            {isUserInGroup('Admin') ? <AdminComponent
            cognitoUser={cognitoUser}
            /> : null}
        </div>
    );
}

Header.displayName="Header";

export default Header;