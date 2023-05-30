import React from "react";
import {Amplify} from "aws-amplify";
import {Authenticator} from "@aws-amplify/ui-react";
import '@aws-amplify/ui-react/styles.css';
import awsExports from "./aws-exports";
import Footer from "./components/footer";
import Pillar from "./components/pillar";
import Header from "./components/header";



Amplify.configure(awsExports);


function App () {

    return (
    <Authenticator>
        {
            ({ signOut, user }) => (
                <main>
                    <Header/>
                    <button onClick={signOut}>Sign out</button>
                    <Pillar/>
                    <Footer/>
                </main>
            )}
    </Authenticator>
    );
}

export default App;