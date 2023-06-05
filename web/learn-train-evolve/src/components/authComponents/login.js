import {Authenticator} from "@aws-amplify/ui-react";
import Header from "../../routes/Header";
import Footer from "../Footer";
import React from "react";
import {Amplify} from "aws-amplify";
import '@aws-amplify/ui-react/styles.css';
import awsExports   from "../../aws-exports";


Amplify.configure(awsExports);
function Login () {
    return (
        <Authenticator>
            {
                ({ signOut, user }) => (
                    <main>
                        <Header/>
                        <button onClick={signOut}>Sign out</button>
                        <Footer/>
                    </main>
                )}
        </Authenticator>
    );

}

export default Login;