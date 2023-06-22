import {Amplify, Auth} from 'aws-amplify';

import {Authenticator, Button, Heading, useTheme,} from '@aws-amplify/ui-react';
import '@aws-amplify/ui-react/styles.css';

import awsExports from '../../aws-exports';
import React, {useEffect} from "react";
import Home from "../Home";
import AdminComponent from "../AdminComponent";
import Header from "../Header";
Amplify.configure(awsExports);

export default function Login() {
    const {tokens} = useTheme();


    const signinStyle = {
        display: 'flex',
        justifyContent: 'flex-end',
    }
return (
        <Authenticator>
            {({ signOut, user }) => (
                <main>
                    <div style={signinStyle}>
                    <Button
                        variation="menu"
                        border={tokens.borderWidths.small}
                        borderRadius={tokens.radii.large}
                        onClick={signOut}
                    >Sign Out</Button>
                    <AdminComponent
                        cognitoUser={user}
                    /></div>
                    <Home
                    cognitoUser={user}
                    />
                </main>
            )}
        </Authenticator>
    );
}