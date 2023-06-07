import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Login from "./components/authComponents/login"
import Home from "./components/Home";




function App () {
        return (
            <div>
                <Header/>
                <Login/>
                <Home/>
                <Footer/>
            </div> );

};

export default App;

