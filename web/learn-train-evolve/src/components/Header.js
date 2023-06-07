import React from "react";
import '../styles.css';

import { useEffect, useState } from 'react';
import { Auth } from 'aws-amplify';

function Header() {
    const [userGroups, setUserGroups] = useState([]);

    useEffect(() => {
        async function fetchUserGroups() {
            try {
                const user = await Auth.currentAuthenticatedUser();
                console.log(user);
                const groups = user.signInUserSession.accessToken.payload['cognito:groups'];
                setUserGroups(groups || []);
            } catch (error) {
                console.log('Error fetching user groups:', error);
            }
        }
        fetchUserGroups();
    }, []);

    const isUserInGroup = (groupName) => {
        return userGroups.includes(groupName);
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
        </div>
    );
}


export default Header;