import React from "react";
import '../styles.css';

import { useEffect, useState } from 'react';
import { Auth } from 'aws-amplify';
import AdminComponent from "./AdminComponent";
import App from "../App";

function Header() {



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

Header.displayName="Header";

export default Header;