import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Login from "./components/authComponents/login"


function App () {

        return (
            <div>
                <Header/>
                <Login/>
                <Footer/>
            </div> );

}

App.displayName="App";

export default App;

