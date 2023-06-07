import { Amplify } from 'aws-amplify';

import {Authenticator, Button, Heading, useTheme,} from '@aws-amplify/ui-react';
import '@aws-amplify/ui-react/styles.css';

import awsExports from '../../aws-exports';
import React from "react";
Amplify.configure(awsExports);

export default function Login() {
    const {tokens} = useTheme();
    return (
        <Authenticator>
            {({ signOut, user }) => (
                <main>
                    <Heading level={3} textAlign={"center"}> Welcome {user.attributes.name}</Heading>
                    <Button
                        variation="menu"
                        border={tokens.borderWidths.small}
                        borderRadius={tokens.radii.large}
                        onClick={signOut}
                    >Sign Out</Button>

                </main>
            )}
        </Authenticator>
    );
}