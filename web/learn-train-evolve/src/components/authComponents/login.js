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


return (
        <Authenticator>
            {({ signOut, user }) => (
                <main>
                    <Heading level={4} color={tokens.colors.brand.primary[100]} textAlign={"center"}> Welcome {user.attributes.name}</Heading>
                    <Button
                        variation="menu"
                        border={tokens.borderWidths.small}
                        borderRadius={tokens.radii.large}
                        onClick={signOut}
                    >Sign Out</Button>
                    <AdminComponent
                        cognitoUser={user}
                    />
                    <Home
                    cognitoUser={user}
                    />
                </main>
            )}
        </Authenticator>
    );
}